package com.phoshp.molang.parser;

import com.phoshp.molang.parser.tokenizer.Token;

public interface Parselet {

    Expression parse(MoLangParser parser, Token token);
}
