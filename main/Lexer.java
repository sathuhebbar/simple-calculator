package main;

import java.math.BigDecimal;

public class Lexer {
    private String expression;
    private int idx;

    Lexer(String expression) {
        this.expression = expression;
        idx = 0;
    }

    private char nextChar() {
        return expression.charAt(idx++);
    }

    public boolean charsLeft() {
        return idx < expression.length();
    }

    private char peek() {
        return expression.charAt(idx);
    }

    public Token next() {
        if (!charsLeft())
            return null;
        char c = nextChar();
        if (Character.isWhitespace(c))
            while (Character.isWhitespace(c = nextChar())) ;
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '=':
            case '^':
                return new Token(TokenType.typeWithCharacteristicLexeme("" + c));
        }
        if (Character.isDigit(c)) {
            StringBuilder number_string = new StringBuilder();
            number_string.append(c);
            boolean found_dcpoint = false;
            while (charsLeft() && Character.isDigit(c = peek()) || c == '.') {
                if (c == '.') {
                    if (found_dcpoint)
                        break;
                    else found_dcpoint = true;
                }
                number_string.append(c);
                nextChar();
            }
            return new Token(new BigDecimal(number_string.toString()));
        } else if (Character.isLetter(c)) {
            StringBuilder identifier = new StringBuilder();
            identifier.append(c);
            while (charsLeft() && Character.isLetter(c = peek()) || c == '_') {
                identifier.append(c);
                nextChar();
            }
            return new Token(identifier.toString());
        }
        throw new RuntimeException();
    }

}
