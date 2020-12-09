package com.bedrockk.molang;

import com.bedrockk.molang.parser.ExprTraverser;
import com.bedrockk.molang.parser.ExprVisitor;
import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.runtime.MoLangRuntime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@DisplayName("Evaluator Test")
public class EvaluatorTest {

    @Test
    public void testEval() throws IOException {
        MoLangParser parser = MoLang.newParser(getClass().getClassLoader().getResourceAsStream("expr4.txt"));
        MoLangRuntime runtime = MoLang.newRuntime();

        List<Expression> expressions = parser.parse();
        System.out.println("Eval Test Result: " + runtime.execute(expressions).asDouble());

        ExprTraverser traverser = new ExprTraverser();
        traverser.getVisitors().add(expression -> {
            if (expression.getAttributes().containsKey("parent")) {
                System.out.println("Oh now: " + expression.getAttributes().get("parent"));
            }

            return null;
        });
        traverser.traverse(expressions);
    }
}
