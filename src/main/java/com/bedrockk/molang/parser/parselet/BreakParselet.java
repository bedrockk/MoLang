package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.BreakExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class BreakParselet extends PrefixParselet {

    public BreakParselet(Precedence precedence) {
        super(precedence);
    }

    public BreakParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new BreakExpression();
    }
}
