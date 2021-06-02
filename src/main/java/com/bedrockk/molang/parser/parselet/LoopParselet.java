package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.ast.LoopExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

import java.util.List;

public class LoopParselet implements PrefixParselet {

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
