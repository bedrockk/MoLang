package com.bedrockk.molang.parser;

import com.bedrockk.molang.parser.tokenizer.Token;

public interface Parselet {

    Expression parse(MoLangParser parser, Token token);
}
