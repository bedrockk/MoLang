package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.ContinueExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class ContinueParselet extends PrefixParselet {

    public ContinueParselet(Precedence precedence) {
        super(precedence);
    }

    public ContinueParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new ContinueExpression();
    }
}
