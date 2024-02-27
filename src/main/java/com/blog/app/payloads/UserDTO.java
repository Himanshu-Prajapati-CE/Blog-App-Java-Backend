package com.blog.app.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotNull
    @Size(min = 4, message = "User Name must be min 4 char!!")
    private String name;

    @Email(message = "Email is not valid!")
    private String email;

    @NotEmpty(message = "Password should not be empty!!")
    @Size(min = 3, max = 15, message = "Password must be min 3 char and max 15 char")
    private String password;

    @NotEmpty
    private String about;
}
