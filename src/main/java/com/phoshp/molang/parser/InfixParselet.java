package com.phoshp.molang.parser;

import com.phoshp.molang.parser.tokenizer.Token;

public interface InfixParselet {

    Expression parse(MoLangParser parser, Token token, Expression leftExpr);
}
