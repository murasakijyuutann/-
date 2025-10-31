package mjyuu.vocaloidshop.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MemberDTO Validation Tests")
class MemberDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Valid MemberDTO should pass validation")
    void testValidMemberDTO() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("user@example.com");
        member.setPassword("password123");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Invalid email format in name field should fail validation")
    void testInvalidEmailFormatInName() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("invalid-email");
        member.setEmail("user@example.com");
        member.setPassword("password123");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid email format");
    }

    @Test
    @DisplayName("Email exceeding 50 characters should fail validation")
    void testEmailTooLong() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("verylongemailaddressthatexceedsfiftycharacters@example.com");
        member.setEmail("user@example.com");
        member.setPassword("password123");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Email must be at most 50 characters");
    }

    @Test
    @DisplayName("Blank email should fail validation")
    void testBlankEmail() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("");
        member.setPassword("password123");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Email is required");
    }

    @Test
    @DisplayName("Invalid email format should fail validation")
    void testInvalidEmailFormat() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("invalid-email");
        member.setPassword("password123");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid email format");
    }

    @Test
    @DisplayName("Blank password should fail validation")
    void testBlankPassword() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("user@example.com");
        member.setPassword("");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then - Blank password triggers both @NotBlank and @Size validations
        assertThat(violations).hasSize(2);
        assertThat(violations)
            .extracting(ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder("Password is required", "Password must be at least 8 characters");
    }

    @Test
    @DisplayName("Password shorter than 8 characters should fail validation")
    void testPasswordTooShort() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("user@example.com");
        member.setEmail("user@example.com");
        member.setPassword("short");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Password must be at least 8 characters");
    }

    @Test
    @DisplayName("Multiple validation errors should be caught")
    void testMultipleValidationErrors() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName("invalid-name");
        member.setEmail("");
        member.setPassword("short");

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(3);
    }

    @Test
    @DisplayName("Null values should fail validation")
    void testNullValues() {
        // Given
        MemberDTO member = new MemberDTO();
        member.setName(null);
        member.setEmail(null);
        member.setPassword(null);

        // When
        Set<ConstraintViolation<MemberDTO>> violations = validator.validate(member);

        // Then
        assertThat(violations).hasSize(2); // Only email and password have @NotBlank
    }
}