package com.example.accounter.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
// @ToString
// @EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;
    private String email;
    private String password;
}
