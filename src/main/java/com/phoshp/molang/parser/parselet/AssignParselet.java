package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.InfixParselet;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.AssignExpression;
import com.phoshp.molang.parser.tokenizer.Token;

public class AssignParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        return new AssignExpression(leftExpr, parser.parseExpression());
    }
}
