package com.bedrockk.molang;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

@DisplayName("Parse")
public class ParseTest {

    @Test
    @DisplayName("Parse File 1")
    public void parse1() throws IOException {
        MoLang.newParser(getClass().getClassLoader().getResourceAsStream("parse_test.txt"));
    }

    @Test
    @DisplayName("Parse File 2")
    public void parse2() throws IOException {
        MoLang.newParser(getClass().getClassLoader().getResourceAsStream("parse_test2.txt"));
    }
}
