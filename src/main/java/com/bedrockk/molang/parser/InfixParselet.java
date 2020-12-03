package com.bedrockk.molang.parser;

import com.bedrockk.molang.parser.tokenizer.Token;

public abstract class InfixParselet {

    private final Precedence precedence;

    public InfixParselet() {
        this.precedence = Precedence.ANYTHING;
    }
    public InfixParselet(Precedence precedence) {
        this.precedence = precedence;
    }

    public abstract Expression parse(MoLangParser parser, Token token, Expression leftExpr);

    public Precedence getPrecedence() {
        return precedence;
    }
}