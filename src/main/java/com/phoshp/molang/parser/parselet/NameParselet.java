package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.FuncCallExpression;
import com.phoshp.molang.parser.expression.NameExpression;
import com.phoshp.molang.parser.tokenizer.Token;

import java.util.List;

public class NameParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        List<Expression> args = parser.parseArgs();
        String name = parser.fixNameShortcut(token.getText());

        Expression nameExpr = new NameExpression(token.getText());

        if (args.size() > 0 || parser.getNameHead(name).equals("query") || parser.getNameHead(name).equals("math")){
            return new FuncCallExpression(nameExpr, args.toArray(new Expression[0]));
        }

        return nameExpr;
    }
}
