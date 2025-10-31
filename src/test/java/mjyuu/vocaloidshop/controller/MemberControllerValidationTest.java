package mjyuu.vocaloidshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import mjyuu.vocaloidshop.dto.MemberDTO;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("MemberController Validation Integration Tests")
class MemberControllerValidationTest {

    @Autowired
    private MemberController memberController;
    
    @Autowired
    private Validator validator;

    @Test
    @DisplayName("Controller should validate invalid email format")
    void testControllerValidationInvalidEmail() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("invalid-email");
        member.setPassword("password123");

        // Create a DataBinder to simulate form binding and validation
        DataBinder binder = new DataBinder(member);
        binder.addValidators(validator);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        // When
        String result = memberController.add(member, bindingResult);

        // Then
        assertThat(bindingResult.hasErrors()).isTrue();
        assertThat(result).isEqualTo("member/addForm");
    }

    @Test
    @DisplayName("Controller should validate short password")
    void testControllerValidationShortPassword() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("user@example.com");
        member.setPassword("short");

        // Create a DataBinder to simulate form binding and validation
        DataBinder binder = new DataBinder(member);
        binder.addValidators(validator);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        // When
        String result = memberController.add(member, bindingResult);

        // Then
        assertThat(bindingResult.hasErrors()).isTrue();
        assertThat(result).isEqualTo("member/addForm");
    }

    @Test
    @DisplayName("Controller should validate blank email")
    void testControllerValidationBlankEmail() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("");
        member.setPassword("password123");

        // Create a DataBinder to simulate form binding and validation
        DataBinder binder = new DataBinder(member);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        // When
        String result = memberController.add(member, bindingResult);

        // Then
        assertThat(bindingResult.hasErrors()).isTrue();
        assertThat(result).isEqualTo("member/addForm");
    }

    @Test
    @DisplayName("Controller should accept valid member data")
    void testControllerValidationValidData() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("user@example.com");
        member.setPassword("password123");

        // Create a DataBinder without validation errors
        DataBinder binder = new DataBinder(member);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        // When
        String result = memberController.add(member, bindingResult);

        // Then
        assertThat(bindingResult.hasErrors()).isFalse();
        assertThat(result).isEqualTo("redirect:/members");
    }
}