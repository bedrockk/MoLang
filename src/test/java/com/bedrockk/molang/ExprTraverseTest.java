package com.bedrockk.molang;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.ExprFinder;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.expression.FuncCallExpression;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@DisplayName("Expression Traverse")
public class ExprTraverseTest {

    @Test
    @DisplayName("Test Find")
    public void testFind() throws IOException {
        MoLangParser parser = MoLang.newParser(getClass().getClassLoader().getResourceAsStream("parse_test2.txt"));

        List<Expression> found = ExprFinder.find(parser.parse(), expression -> expression instanceof FuncCallExpression);

        Assertions.assertEquals(4, found.size());
    }

    @Test
    @DisplayName("Test Find 2")
    public void testFind2() throws IOException {
        MoLangParser parser = MoLang.newParser(getClass().getClassLoader().getResourceAsStream("parse_test2.txt"));

        System.out.println("\n" + (new GsonBuilder()).setPrettyPrinting().create().toJson(parser.parse()));
    }
}
