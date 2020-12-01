package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.NumberExpression;
import com.phoshp.molang.parser.tokenizer.Token;

public class NumberParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new NumberExpression(Float.parseFloat(token.getText()));
    }
}
