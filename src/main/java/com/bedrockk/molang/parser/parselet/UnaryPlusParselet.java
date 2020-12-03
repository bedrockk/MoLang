package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.UnaryPlusExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class UnaryPlusParselet extends PrefixParselet {

    public UnaryPlusParselet(Precedence precedence) {
        super(precedence);
    }

    public UnaryPlusParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new UnaryPlusExpression(parser.parseExpression(getPrecedence()));
    }
}
