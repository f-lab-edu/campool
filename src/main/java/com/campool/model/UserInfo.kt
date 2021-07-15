package com.campool.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class UserInfo {

    private final String id;

    private final String name;

    private final String email;

    private final String telephone;


}
