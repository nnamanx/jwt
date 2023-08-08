package com.company.jwt.model.dto.request;

import com.company.jwt.model.constant.Constants;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @NotBlank(message = Constants.EMAIL_IS_MANDATORY)
    @Email
    String email;

    @NotBlank(message = Constants.PASSWORD_IS_MANDATORY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%])[A-Za-z\\d@#$%]{8,}$", message = Constants.PASSWORD_REGEX)
    String password;
}
