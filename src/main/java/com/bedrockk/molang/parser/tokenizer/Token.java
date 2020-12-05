package com.bedrockk.molang.parser.tokenizer;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Token {
    TokenType type;
    String text;
    TokenPosition position;

    public Token(TokenType tokenType, TokenPosition position) {
        this.type = tokenType;
        this.text = tokenType.getSymbol();
        this.position = position;
    }
}
