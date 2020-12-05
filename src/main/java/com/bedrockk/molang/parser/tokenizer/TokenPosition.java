package com.bedrockk.molang.parser.tokenizer;

import lombok.Value;

@Value
public class TokenPosition {

    int startLineNumber;
    int endLineNumber;
    int startColumn;
    int endColumn;
}
