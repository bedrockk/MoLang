package com.bedrockk.molang.parser.tokenizer;

public enum TokenType {
    EQUALS("=="),
    NOT_EQUALS("!="),
    COALESCE("??"),
    AND("&&"),
    OR("||"),
    GREATER_OR_EQUALS(">="),
    SMALLER_OR_EQUALS("<="),
    ARROW("->"),

    GREATER(">"),
    SMALLER("<"),
    BRACKET_LEFT("("),
    BRACKET_RIGHT(")"),
    ARRAY_LEFT("["),
    ARRAY_RIGHT("]"),
    CURLY_BRACKET_LEFT("{"),
    CURLY_BRACKET_RIGHT("}"),
    COMMA(","),
    ASSIGN("="),
    PLUS("+"),
    MINUS("-"),
    ASTERISK("*"),
    SLASH("/"),
    QUESTION("?"),
    COLON(":"),
    SEMICOLON(";"),
    BANG("!"),

    RETURN("return"),
    CONTINUE("continue"),
    BREAK("break"),
    FOR_EACH("for_each"),
    LOOP("loop"),
    THIS("this"),
    TRUE("true"),
    FALSE("false"),
    STRING(""),
    NUMBER(""),
    NAME(""),
    EOF("");

    public final String symbol;

    TokenType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TokenType bySymbol(String symbol) {
        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.getSymbol().equals(symbol)) {
                return tokenType;
            }
        }

        return null;
    }
}
