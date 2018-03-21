package main;

import java.util.Objects;

/**
 * Represents different 'types' of tokens. In case of operators, the
 * type itself is unique to the operator and thus the lexeme of the
 * operator is a characteristic of the type itself, referred to as its
 * characteristic lexeme. For other types, the characteristic lexeme
 * is {@code null}, as they cannot be inferred from the type itself.
 */
public enum TokenType {
    NUMBER(null),
    IDENTIFIER(null),
    ADD_SYMBOL("+"),
    SUB_SYMBOL("-"),
    MUL_SYMBOL("*"),
    DIV_SYMBOL("/"),
    ASSIGNMENT("=");
    POWER_SYMBOL("^");

    // The characteristic lexeme
    final String ch_lexeme;
    TokenType(String ch_lexeme) {
        this.ch_lexeme = ch_lexeme;
    }

    /**
     * Returns the type with the specified characteristic lexeme, if
     * there exists one, and {@code null} otherwise.
     * @param ch_lexeme the characteristic lexeme
     * @return the type with the specified characteristic lexeme, if
     * there exists one, and null otherwise
     */
    static TokenType typeWithCharacteristicLexeme(String ch_lexeme) {
        for (TokenType t : values()) {
            if(Objects.equals(t.ch_lexeme,ch_lexeme))
                return t;
        }
        return null;
    }
}
