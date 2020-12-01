package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.ForEachExpression;
import com.phoshp.molang.parser.tokenizer.Token;

import java.util.List;

public class ForEachParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        List<Expression> args = parser.parseArgs();

        if (args.size() != 3) {
            throw new RuntimeException("ForEach: Expected 3 argument, " + args.size() + " argument given");
        } else {
            return new ForEachExpression(args.get(0), args.get(1), args.get(2));
        }
    }
}
