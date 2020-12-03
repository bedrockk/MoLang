package com.bedrockk.molang.parser;

import com.bedrockk.molang.parser.tokenizer.Token;

public abstract class PrefixParselet {

    private final Precedence precedence;

    public PrefixParselet() {
        this.precedence = Precedence.ANYTHING;
    }

    public PrefixParselet(Precedence precedence) {
        this.precedence = precedence;
    }

    public abstract Expression parse(MoLangParser parser, Token token);

    public Precedence getPrecedence() {
        return precedence;
    }
}
