package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.parser.InfixParselet;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.ast.AssignExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class AssignParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        return new AssignExpression(leftExpr, parser.parseExpression(getPrecedence()));
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.ASSIGNMENT;
    }
}
