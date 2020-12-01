package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.BooleanExpression;
import com.phoshp.molang.parser.tokenizer.Token;

public class BooleanParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new BooleanExpression(Boolean.parseBoolean(token.getText()));
    }
}
