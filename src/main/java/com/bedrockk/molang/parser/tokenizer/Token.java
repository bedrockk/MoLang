package com.bedrockk.molang.parser.tokenizer;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Token {
    TokenType type;
    String text;

    public Token(TokenType tokenType) {
        this.type = tokenType;
        this.text = tokenType.getSymbol();
    }
}
