package com.bedrockk.molang.parser.tokenizer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPosition {

    private int startLineNumber;
    private int endLineNumber;
    private int startColumn;
    private int endColumn;
}
