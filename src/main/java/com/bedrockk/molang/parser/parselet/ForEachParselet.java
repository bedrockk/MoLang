package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.ForEachExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

import java.util.List;

public class ForEachParselet extends PrefixParselet {

    public ForEachParselet(Precedence precedence) {
        super(precedence);
    }

    public ForEachParselet() {
        super();
    }

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
