package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.PrefixParselet;
import com.bedrockk.molang.parser.expression.BooleanExpression;
import com.bedrockk.molang.parser.tokenizer.Token;
import lombok.AllArgsConstructor;
import lombok.Value;

public class BooleanParselet extends PrefixParselet {

    public BooleanParselet(Precedence precedence) {
        super(precedence);
    }

    public BooleanParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new BooleanExpression(Boolean.parseBoolean(token.getText()));
    }
}
