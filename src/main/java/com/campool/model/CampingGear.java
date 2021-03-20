package com.campool.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CampingGear {

    private final String name;

    private final int typeId;

    private final int count;

}
