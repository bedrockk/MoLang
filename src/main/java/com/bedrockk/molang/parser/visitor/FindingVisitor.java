package com.bedrockk.molang.parser.visitor;

import com.bedrockk.molang.parser.ExprTraverser;
import com.bedrockk.molang.parser.ExprVisitor;
import com.bedrockk.molang.parser.Expression;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class FindingVisitor implements ExprVisitor {

    private final Predicate<Expression> predicate;
    private final List<Expression> foundExpressions = new ArrayList<>();

    @Override
    public Object onVisit(Expression expression) {
        if (predicate.test(expression)) {
            foundExpressions.add(expression);
        }

        return null;
    }

    public List<Expression> getFoundExpressions() {
        return foundExpressions;
    }
}
