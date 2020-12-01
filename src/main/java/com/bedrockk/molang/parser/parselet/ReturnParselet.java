package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Parselet;
import com.bedrockk.molang.parser.expression.ReturnExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class ReturnParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new ReturnExpression(parser.parseExpression());
    }
}
