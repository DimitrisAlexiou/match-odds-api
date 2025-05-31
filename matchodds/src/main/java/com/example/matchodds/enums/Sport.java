package com.example.matchodds.enums;

import lombok.Getter;

@Getter
public enum Sport {
    FOOTBALL(1, "Football"),
    BASKETBALL(2, "Basketball");

    private final int code;
    private final String label;

    Sport(int code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static Sport fromCode(int code) {
        for (Sport sport : Sport.values()) {
            if (sport.getCode() == code) {
                return sport;
            }
        }
        throw new IllegalArgumentException("Invalid sport code: " + code);
    }
}
