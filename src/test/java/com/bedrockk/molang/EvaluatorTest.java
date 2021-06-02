package com.bedrockk.molang;

import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.runtime.MoLangRuntime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@DisplayName("Evaluator Test")
public class EvaluatorTest {

    private void eval(String file, double expected) throws IOException {
        var parsed = MoLang.parse(getClass().getClassLoader().getResourceAsStream(file));
        var runtime = MoLang.createRuntime();
        var actual = runtime.execute(parsed).asDouble();

        Assertions.assertEquals(Math.round(expected), Math.round(actual));
    }

    @Test
    public void testEval3() throws IOException {
        eval("expr3.txt", (213 + 2 / 0.5 + 5 + 2 * 3));
    }

    @Test
    public void testEval4() throws IOException {
        eval("expr4.txt", (213 + 2 / 0.5 + 5 + 2 * 3) + 310.5 + (10 * Math.cos(270)) + 100);
    }

}
