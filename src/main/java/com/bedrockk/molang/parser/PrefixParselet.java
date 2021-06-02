package com.bedrockk.molang.parser;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.parser.tokenizer.Token;

public interface PrefixParselet {

    Expression parse(MoLangParser parser, Token token);
}
