package com.phoshp.molang;

import com.google.gson.GsonBuilder;
import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.tokenizer.Token;
import com.phoshp.molang.parser.tokenizer.TokenIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

@DisplayName("Parse")
public class ParseTest {

    @Test
    @DisplayName("Parse File 1")
    public void parse1() throws IOException {
        dumpExpressions("parse_test.txt");
    }

    @Test
    @DisplayName("Parse File 2")
    public void parse2() throws IOException {
        dumpExpressions("parse_test2.txt");
    }

    public void dumpExpressions(String fileName) throws IOException {
        String code = readFile(getClass().getClassLoader().getResourceAsStream(fileName));
        System.out.println("Raw Code: \n" + code);

        MoLangParser parser = new MoLangParser(new TokenIterator(code));
        System.out.println((new GsonBuilder()).setPrettyPrinting().create().toJson(parser.parse()));
    }

    public static String readFile(InputStream inputStream) throws IOException {
        return readFile(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    private static String readFile(Reader reader) throws IOException {
        try (BufferedReader br = new BufferedReader(reader)) {
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            temp = br.readLine();
            while (temp != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(temp);
                temp = br.readLine();
            }
            return stringBuilder.toString();
        }
    }
}
