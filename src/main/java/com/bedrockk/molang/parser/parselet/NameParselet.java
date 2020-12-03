package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.FuncCallExpression;
import com.bedrockk.molang.parser.expression.NameExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

import java.util.List;

public class NameParselet extends PrefixParselet {

    public NameParselet(Precedence precedence) {
        super(precedence);
    }

    public NameParselet() {
        super();
    }

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
