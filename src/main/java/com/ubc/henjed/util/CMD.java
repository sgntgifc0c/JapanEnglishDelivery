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

    public static boolean YorN(String msg) {
        boolean result = false;
        boolean resultGiven = false;
        CMD.msg(msg + " (Sim ou Não):");

        while (!resultGiven) {
            switch (scanner.next()) {
                case "S":
                case "s":
                case "Sim":
                case "sim":
                    result = true;
                    resultGiven = true;
                    break;
                case "N":
                case "n":
                case "Não":
                case "Nao":
                case "não":
                case "nao":
                    result = false;
                    resultGiven = true;
                    break;
                default:
                    CMD.msg("Opção errada, digite denovo");
                    break;
            }
        }

        return result;
    }
}
