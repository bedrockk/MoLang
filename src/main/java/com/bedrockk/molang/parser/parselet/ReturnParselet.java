package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.ReturnExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class ReturnParselet extends PrefixParselet {

    public ReturnParselet(Precedence precedence) {
        super(precedence);
    }

    public ReturnParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new ReturnExpression(parser.parseExpression(getPrecedence()));
    }
}
