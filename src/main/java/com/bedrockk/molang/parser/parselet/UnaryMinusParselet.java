package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.UnaryMinusExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class UnaryMinusParselet extends PrefixParselet {

    public UnaryMinusParselet(Precedence precedence) {
        super(precedence);
    }

    public UnaryMinusParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new UnaryMinusExpression(parser.parseExpression(getPrecedence()));
    }
}
