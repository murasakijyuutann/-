package mjyuu.vocaloidshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@AllArgsConstructor 
@NoArgsConstructor  
@ToString
@Setter
@Getter
public class MemberDTO {
    private Long id;
    
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email must be at most 50 characters")
	private String name;

    @NotBlank( message = "Email is required" )
    @Email( message = "Invalid email format" )
    private String email;
    
    @NotBlank( message = "Password is required" )
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
