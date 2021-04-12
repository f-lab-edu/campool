package com.campool.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(1),
    ADMIN(2);
    private final int order;
}
