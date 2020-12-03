package com.bedrockk.molang.parser;

import com.bedrockk.molang.parser.visitor.FindingVisitor;

import java.util.List;
import java.util.function.Predicate;

public class ExprFinder {

    public static List<Expression> find(List<Expression> expressions, Predicate<Expression> predicate) {
        ExprTraverser traverser = new ExprTraverser();
        FindingVisitor visitor = new FindingVisitor(predicate);

        traverser.getVisitors().add(visitor);
        traverser.traverse(expressions);

        return visitor.getFoundExpressions();
    }
}
