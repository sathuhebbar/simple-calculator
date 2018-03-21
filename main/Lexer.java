package main;

import java.math.BigDecimal;

/**
 * Instances of this class represent lexers of expressions. Lexers
 * accept an expression and produce tokens from it.
 *
 * @see Token
 * @see TokenType
 */
public class Lexer {

    /**
     * The expression from which tokens are to be produced.
     */
    private String expression;

    /**
     * Stores the index of the next character to be
     * read from the expression to produce a token.
     */
    private int idx;

    /**
     * Creates a new lexer whose expression is the given expression
     * @param expression the expression
     */
    Lexer(String expression) {
        this.expression = expression;
        idx = 0;
    }

    /**
     * Returns the next character from the expression for producing
     * a token, {@code idx} now points to the next character in the
     * expression.
     * @return the next character from the expression for producing
     * a token
     */
    private char nextChar() {
        return expression.charAt(idx++);
    }

    /**
     * Returns true if there are any characters left in the expression
     * for reading and tokenisation and false otherwise.
     * @return true if there are any characters left in the expression
     * for reading and tokenisation, false otherwise
     */
    public boolean charsLeft() {
        return idx < expression.length();
    }

    /**
     * Returns the next character from the expression for producing
     * a token, {@code idx} remains unaltered. Two successive calls
     * to this method will return the same character.
     * @return the next character from the expression for producing
     * a token
     */
    private char peek() {
        return expression.charAt(idx);
    }
    public Token next() {
        if(!charsLeft())
            return null;
        char c = nextChar();
        if(Character.isWhitespace(c))
            while (Character.isWhitespace(c = nextChar()));
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '=':
            case '^':
                return new Token(TokenType.typeWithCharacteristicLexeme("" + c));
        }
        if(Character.isDigit(c)) {
            StringBuilder number_string = new StringBuilder();
            number_string.append(c);
            boolean found_dcpoint = false;
            while(charsLeft() && Character.isDigit(c = peek()) || c == '.') {
                if (c == '.') {
                    if (found_dcpoint)
                        break;
                    else found_dcpoint = true;
                }
                number_string.append(c);
                nextChar();
            }
            return new Token(new BigDecimal(number_string.toString()));
        }
        else if(Character.isLetter(c)) {
            StringBuilder identifier = new StringBuilder();
            identifier.append(c);
            while(charsLeft() && Character.isLetter(c = peek()) || c == '_') {
                identifier.append(c);
                nextChar();
            }
            return new Token(identifier.toString());
        }
        throw new RuntimeException();
    }

}
