package com.codecool.service;

import com.codecool.model.Board;

public interface Converter {
    Board convert(String input) throws  IllegalArgumentException;
}
