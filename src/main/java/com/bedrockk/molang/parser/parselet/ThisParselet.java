package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.ThisExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class ThisParselet extends PrefixParselet {

    public ThisParselet(Precedence precedence) {
        super(precedence);
    }

    public ThisParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new ThisExpression();
    }
}
