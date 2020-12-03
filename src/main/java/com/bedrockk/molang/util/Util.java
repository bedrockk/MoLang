package com.bedrockk.molang.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    @SuppressWarnings("unchecked")
    public static <T> T[] mergeItems(T[] type, Object... args) {
        List<T> result = new ArrayList<>();

        for (Object arg : args) {
            if (arg.getClass().isArray()) {
                result.addAll(Arrays.asList((T[]) arg));
            } else {
                result.add((T) arg);
            }
        }

        return result.toArray(type);
    }

    public static String readFile(InputStream inputStream) throws IOException {
        return readFile(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    private static String readFile(Reader reader) throws IOException {
        try (BufferedReader br = new BufferedReader(reader)) {
            String temp = br.readLine();
            StringBuilder stringBuilder = new StringBuilder();

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
