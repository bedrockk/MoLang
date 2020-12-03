package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.NumberExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class NumberParselet extends PrefixParselet {

    public NumberParselet(Precedence precedence) {
        super(precedence);
    }

    public NumberParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new NumberExpression(Float.parseFloat(token.getText()));
    }
}
