package pojo.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderColor {
    NO_COLOR(new String[]{}),
    GRAY(new String[]{"GRAY"}),
    TWO_COLOR(new String[]{"BLACK", "GRAY"});

    private final String[] value;

}