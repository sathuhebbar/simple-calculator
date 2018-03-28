package main;

import java.util.Objects;

public enum TokenType {
    NUMBER(null),
    IDENTIFIER(null),
    ADD_SYMBOL("+"),
    SUB_SYMBOL("-"),
    MUL_SYMBOL("*"),
    DIV_SYMBOL("/"),
    ASSIGNMENT("="),
    POWER_SYMBOL("^");

    final String ch_lexeme;

    TokenType(String ch_lexeme) {
        this.ch_lexeme = ch_lexeme;
    }

    static TokenType typeWithCharacteristicLexeme(String ch_lexeme) {
        for (TokenType t : values()) {
            if (Objects.equals(t.ch_lexeme, ch_lexeme))
                return t;
        }
        return null;
    }
}
