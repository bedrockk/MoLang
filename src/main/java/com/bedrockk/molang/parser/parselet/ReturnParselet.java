package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.ReturnExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class ReturnParselet implements PrefixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new ReturnExpression(parser.parseExpression());
    }
}
