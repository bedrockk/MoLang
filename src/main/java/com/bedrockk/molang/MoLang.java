package com.bedrockk.molang;

import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.tokenizer.TokenIterator;
import com.bedrockk.molang.runtime.MoLangRuntime;
import com.bedrockk.molang.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class MoLang {

    public static MoLangParser newParser(String code) {
        return new MoLangParser(new TokenIterator(code));
    }

    public static MoLangParser newParser(Path path) {
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            fileBytes = new byte[0];
        }

        return new MoLangParser(new TokenIterator(new String(fileBytes, StandardCharsets.UTF_8)));
    }

    public static MoLangParser newParser(InputStream stream) throws IOException {
        return new MoLangParser(new TokenIterator(Util.readFile(stream)));
    }

    public static MoLangRuntime newRuntime() {
        return new MoLangRuntime();
    }
}
