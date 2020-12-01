package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.LoopExpression;
import com.phoshp.molang.parser.tokenizer.Token;

import java.util.List;

public class LoopParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        List<Expression> args = parser.parseArgs();

        if (args.size() != 2) {
            throw new RuntimeException("Loop: Expected 2 argument, " + args.size() + " argument given");
        } else {
            return new LoopExpression(args.get(0), args.get(1));
        }
    }
}
