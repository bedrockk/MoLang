package com.bedrockk.molang;

import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.runtime.MoLangRuntime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Evaluator Test")
public class EvaluatorTest {

    @Test
    public void testEval() throws IOException {
        MoLangParser parser = MoLang.newParser(getClass().getClassLoader().getResourceAsStream("expr4.txt"));
        MoLangRuntime runtime = MoLang.newRuntime();

        System.out.println("Eval Test Result: " + runtime.execute(parser.parse()).asDouble());
    }
}
