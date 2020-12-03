package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.StringExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class StringParselet extends PrefixParselet {

    public StringParselet(Precedence precedence) {
        super(precedence);
    }

    public StringParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new StringExpression(token.getText());
    }
}
