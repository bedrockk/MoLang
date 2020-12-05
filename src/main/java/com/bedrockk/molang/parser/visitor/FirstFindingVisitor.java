package com.bedrockk.molang.parser.visitor;

import com.bedrockk.molang.parser.ExprTraverser;
import com.bedrockk.molang.parser.ExprVisitor;
import com.bedrockk.molang.parser.Expression;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class FirstFindingVisitor implements ExprVisitor {

    private final Predicate<Expression> predicate;
    private Expression found;

    @Override
    public Object onVisit(Expression expression) {
        if (predicate.test(expression)) {
            found = expression;

            return ExprTraverser.ActionType.STOP_TRAVERSAL;
        }

        return null;
    }

    public Expression getFound() {
        return found;
    }
}
