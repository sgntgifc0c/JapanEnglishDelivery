package com.ubc.henjed.util;

import java.util.Scanner;

public class CMD {
    public static void msg(String msg) {
        System.err.println(msg);
    }

    public static String promptLine(String msg) {
        System.out.print(msg);
        var scan = new Scanner(System.in);
        var result = scan.nextLine();
        scan.close();
        return result;
    }
}
