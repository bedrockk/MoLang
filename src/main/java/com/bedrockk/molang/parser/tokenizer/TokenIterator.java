package com.bedrockk.molang.parser.tokenizer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenIterator {

    private final String code;

    private int index = 0;
    private int currentLine = 0;
    private int lastStep = 0;
    private int lastStepLine = 0;

    public boolean hasNext() {
        return index < code.length();
    }

    public Token next() {
        while (index < code.length()) {
            if (code.length() > index + 1) { // check tokens with double chars
                TokenType token = TokenType.bySymbol(code.substring(index, index + 2));

                if (token != null) {
                    index += 2;
                    return new Token(token, getPosition());
                }
            }

            String expr = getStringAt(index);
            TokenType tokenType = TokenType.bySymbol(expr);
            if (tokenType != null) {
                index++;
                return new Token(tokenType, getPosition());
            } else if (expr.equals("'")) {
                int stringStart = index;
                int stringLength = index + 1;

                while (stringLength < code.length() && !getStringAt(stringLength).equals("'")) {
                    stringLength++;
                }

                stringLength++;
                index = stringLength;

                return new Token(TokenType.STRING, code.substring(stringStart + 1, stringLength - 1), getPosition());
            } else if (Character.isLetter(expr.charAt(0))) {
                int nameLength = index + 1;

                while (nameLength < code.length() && (Character.isLetterOrDigit(getStringAt(nameLength).charAt(0)) || getStringAt(nameLength).equals("_") || getStringAt(nameLength).equals("."))) {
                    nameLength++;
                }

                String value = code.substring(index, nameLength).toLowerCase();
                TokenType token = TokenType.bySymbol(value);

                if (token == null) {
                    token = TokenType.NAME;
                }

                index = nameLength;
                return new Token(token, value, getPosition());
            } else if (Character.isDigit(expr.charAt(0))) {
                int numStart = index;
                int numLength = index + 1;
                boolean hasDecimal = false;

                while (numLength < code.length() && (Character.isDigit(getStringAt(numLength).charAt(0)) || (getStringAt(numLength).equals(".") && !hasDecimal))) {
                    if (getStringAt(numLength).equals(".")) {
                        hasDecimal = true;
                    }
                    numLength++;
                }

                index = numLength;

                return new Token(TokenType.NUMBER, code.substring(numStart, numLength), getPosition());
            } else if (expr.equals("\n") || expr.equals("\r")) {
                currentLine++;
            }

            index++;
        }

        return new Token(TokenType.EOF, getPosition());
    }

    public void step() {
        lastStep = index;
        lastStepLine = currentLine;
    }

    public TokenPosition getPosition() {
        return new TokenPosition(lastStepLine, currentLine, lastStep, index);
    }

    public String getStringAt(String str, int i) {
        return str.substring(i, i + 1);
    }

    public String getStringAt(int i) {
        return code.substring(i, i + 1);
    }
}
