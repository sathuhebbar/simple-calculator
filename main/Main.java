package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter expression:");
        String exp = in.nextLine();
        Lexer l = new Lexer(exp);
        Token t;
        while((t = l.next()) != null)
            System.out.println(t.toString());
    }
}
