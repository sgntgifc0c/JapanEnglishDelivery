package com.ubc.henjed.util;

import java.util.Scanner;

public class CMD {

    private static Scanner scanner;

    public static void setScan(Scanner scan) {
        scanner = scan;
    }

    public static void msg(String msg) {
        System.err.println(msg);
    }

    public static String promptLine(
        String msg,
        int maxChars,
        boolean canBeNull
    ) {
        var canContinue = false;
        var result = "";
        while (!canContinue) {
            System.out.println(msg + " (max. caracteres: " + maxChars + "): ");
            result = scanner.nextLine();
            if (!canBeNull && result.length() == 0) {
                CMD.msg("Valor não pode ser vazio, digite denovo");
                continue;
            }
            if (result.length() > maxChars) {
                CMD.msg("Maximo de caracteres ultrapassado, digite denovo");
                continue;
            }
            canContinue = true;
        }
        return result;
    }

    public static String promptLine(String msg, int maxChars) {
        return promptLine(msg, maxChars, false);
    }
}
