package com.codecool.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorCSVTest {
    private final ValidatorCSV validator;

    ValidatorCSVTest() {
        this.validator = new ValidatorCSV();
    }

    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenEmptyStringPassed() {
        assertThrows(IllegalArgumentException.class, ()->validator.validate(null));
    }

    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenTooShortRowPassed() {
        assertThrows(IllegalArgumentException.class, ()->validator.validate("0;1;2;3;4;5;6;7;"));
    }
    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenNotTooShortColsPassed() {
        assertThrows(IllegalArgumentException.class, ()->validator.validate("0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n"));
    }
    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenNotOnlyDigitsRowPassed() {
        assertThrows(IllegalArgumentException.class, ()->validator.validate("0;1;L;3;D;5;X;7;D;"));
    }

}