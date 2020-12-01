package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.StringExpression;
import com.phoshp.molang.parser.tokenizer.Token;

public class StringParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new StringExpression(token.getText());
    }
}
