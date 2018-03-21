package main;

import java.math.BigDecimal;
import java.util.Objects;
import static main.TokenType.*;

/**
 * A {@code Token} is simply a representative of the smallest valid
 * set of characters in an expression to be parsed by this calculator.
 * Those set of characters are called lexemes. This program handles
 * four major types of tokens, numbers, delimiters and operators and 
 * identifers.
 *
 * @see TokenType
 */
class Token {

    /**
     * The value of the number this token represents,
     * if it represents a number and null otherwise.
     */
    private BigDecimal value;

    /**
     * Representative of what this token represents,
     * i.e, whether it represents a number or an
     * operator etc.
     * @see TokenType
     */
    private TokenType type;

    /**
     * The actual lexeme, required mainly for identifiers
     * as for others they can be inferred from their type
     * or value fields.
     */
    private String lexeme;

    /**
     * Creates a new token with the specified type. Note that
     * this constructor is for operator tokens only, as only
     * their types have characteristic lexemes.
     * @param type the type of token to be created
     * @throws NullPointerException if type == null
     * @throws IllegalArgumentException if type is TokenType.
     * IDENTIFIER or TokenType.NUMBER
     */
    Token(TokenType type) {
        type = Objects.requireNonNull(type);
        switch (type) {
            case NUMBER:
            case IDENTIFIER:
                throw new IllegalArgumentException("Lexeme cannot be inferred");
        }
        this.value = null;
        this.type = type;
        this.lexeme = type.ch_lexeme;
    }

    /**
     * Creates a new token, a token of type {@link TokenType
     * #NUMBER} with the specified value.
     * @param value the value
     * @throws NullPointerException if value == null
     */
    Token(BigDecimal value) {
        value = Objects.requireNonNull(value);
        this.type = NUMBER;
        this.value = value;
        this.lexeme = value.toString();
    }

    /**
     * Creates a new token of type {@link TokenType#IDENTIFIER}
     * which represents the specified identifier.
     * @param id the lexeme, i.e, the identifier
     * @throws NullPointerException if id == null
     */
    Token(String id) {
        id = Objects.requireNonNull(id);
        this.value = null;
        this.type = IDENTIFIER;
        this.lexeme = id;
    }

    /**
     * Returns the type of this token.
     * @return the type of this token
     */
    public TokenType type() {
        return type;
    }

    /**
     * Returns the value represented by this token, if it does
     * and return {@code null} if it does not.
     * @return the value represented by this token, if it does
     * and return null if it does not
     */
    public BigDecimal value() {
        return value;
    }

    /**
     * Returns the lexeme represented by this token.
     * @return the lexeme represented by this token
     */
    public String lexeme() {
        return lexeme;
    }

    /**
     * Returns a string representation of this token.
     * @return a string representation of this token
     */
    public String toString() {
        return type.toString() + ": " + lexeme;
    }
}
