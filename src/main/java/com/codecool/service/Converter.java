package com.codecool.service;

import com.codecool.model.Board;

public interface Converter<T> {
    T convert(String input) throws  IllegalArgumentException;
}
