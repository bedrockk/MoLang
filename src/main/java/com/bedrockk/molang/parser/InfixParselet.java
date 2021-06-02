package com.bedrockk.molang.parser;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.parser.tokenizer.Token;

public interface InfixParselet {

    Expression parse(MoLangParser parser, Token token, Expression leftExpr);

    default Precedence getPrecedence() {
        return Precedence.ANYTHING;
    }
}
