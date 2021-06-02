package com.bedrockk.molang;

import com.bedrockk.molang.ast.ArrayAccessExpression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.ast.FuncCallExpression;
import com.bedrockk.molang.utils.FileUtils;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@DisplayName("Expr Find Tests")
public class ExprTraverseTest {

    @Test
    @DisplayName("Find 1")
    public void testFind() throws IOException {
        var parsed = MoLang.parse(getClass().getClassLoader().getResourceAsStream("expr2.txt"));
        var found = ExprFinder.find(parsed, expression -> expression instanceof FuncCallExpression);

        Assertions.assertEquals(4, found.size());
    }

    @Test
    @DisplayName("Find 2")
    public void testFind2() throws IOException {
        var parsed = MoLang.parse(getClass().getClassLoader().getResourceAsStream("expr2.txt"));
        var found = ExprFinder.find(parsed, expression -> expression instanceof ArrayAccessExpression);

        Assertions.assertEquals(1, found.size());
    }
}
