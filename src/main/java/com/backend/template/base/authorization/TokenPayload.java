package com.backend.template.base.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenPayload {
    private String id;
    private String email;
}
