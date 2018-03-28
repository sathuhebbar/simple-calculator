package main;

import java.math.BigDecimal;
import java.util.Objects;

import static main.TokenType.*;

class Token {
    private BigDecimal value;
    private TokenType type;
    private String lexeme;

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

    Token(BigDecimal value) {
        value = Objects.requireNonNull(value);
        this.type = NUMBER;
        this.value = value;
        this.lexeme = value.toString();
    }

    Token(String id) {
        id = Objects.requireNonNull(id);
        this.value = null;
        this.type = IDENTIFIER;
        this.lexeme = id;
    }

    public TokenType type() {
        return type;
    }

    public BigDecimal value() {
        return value;
    }

    public String lexeme() {
        return lexeme;
    }

    public String toString() {
        return type.toString() + ": " + lexeme;
    }
}
