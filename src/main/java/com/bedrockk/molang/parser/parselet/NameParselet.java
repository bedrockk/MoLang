package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.ast.FuncCallExpression;
import com.bedrockk.molang.ast.NameExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

import java.util.List;

public class NameParselet implements PrefixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        List<Expression> args = parser.parseArgs();
        String name = parser.fixNameShortcut(token.getText());

        Expression nameExpr = new NameExpression(name);

        if (args.size() > 0 || parser.getNameHead(name).equals("query") || parser.getNameHead(name).equals("math")){
            return new FuncCallExpression(nameExpr, args.toArray(new Expression[0]));
        }

        return nameExpr;
    }
}
