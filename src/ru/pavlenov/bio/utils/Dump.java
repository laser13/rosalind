package ru.pavlenov.bio.utils;

import net.sf.kerner.utils.io.UtilIO;
import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.GlobalAlignment;
import ru.pavlenov.bio.amino.Move;

import java.util.Arrays;

/**
 * Created by semen on 22.11.13.
 */
public class Dump {

    public static enum Color {
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m");

        private String code;
        Color(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void ln() {
        System.out.println();
    }

    public static void line() {
        System.out.println("_______________________________________________________");
    }

    public static void line(Color color) {
        System.out.println(color + "========================================================================================================================");
    }

    public static String printfln(Integer[] arr) {

        StringBuilder builder = new StringBuilder();
        builder.append("(");
        String[] out = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) out[i] = "+" + arr[i];
            else out[i] = "" + arr[i];
        }
        builder.append(StringUtils.join(out, " "));
        builder.append(")");

        return builder.toString();

    }

    public static void println(Object obj) {

        if (obj instanceof Float[][]) {

            for (int i = 0; i < ((Float[][]) obj).length; i++) {

                System.out.println(Arrays.<Float>asList((Float[]) ((Float[][]) obj)[i]));

            }
            return;
        }

        if (obj instanceof Byte[][]) {

            for (int i = 0; i < ((Byte[][]) obj).length; i++) {

                System.out.println(Arrays.<Byte>asList((Byte[]) ((Byte[][]) obj)[i]));

            }
            return;
        }

        if (obj instanceof Integer[][]) {

            for (int i = 0; i < ((Integer[][]) obj).length; i++) {

                System.out.println(Arrays.<Integer>asList((Integer[]) ((Integer[][]) obj)[i]));

            }
            return;
        }

        if (obj instanceof String[][]) {

            for (int i = 0; i < ((String[][]) obj).length; i++) {

                System.out.println(Arrays.<String>asList((String[]) ((String[][]) obj)[i]));

            }
            return;
        }

        if (obj instanceof Move[][]) {

            for (int i = 0; i < ((Move[][]) obj).length; i++) {

                System.out.println(Arrays.<Move>asList((Move[]) ((Move[][]) obj)[i]));

            }
            return;
        }

        if (obj instanceof Integer[]) {
            obj = Arrays.<Integer>asList((Integer[]) obj);
        }

        if (obj instanceof byte[]) {
            StringBuilder sb = new StringBuilder();
            for (byte c : (byte[]) obj) {
                sb.append(c).append(", ");
            }
            obj = sb.toString();
        }

        if (obj instanceof int[]) {
            StringBuilder sb = new StringBuilder();
            for (int c : (int[]) obj) {
                sb.append(c).append(", ");
            }
            obj = sb.toString();
        }

        if (obj instanceof String[]) {
            obj = Arrays.<String>asList((String[]) obj);
        }

        if (obj instanceof char[]) {
            StringBuilder sb = new StringBuilder();
            for (char c : (char[]) obj) {
                sb.append(c).append("\t");
            }
            obj = sb.toString();
        }

        if (obj instanceof Object[]) {
            obj = Arrays.<Object>asList((Object[]) obj);
        }

        System.out.println(obj);

    }

    public static void print(Object obj) {

        if (obj instanceof Float[][]) {

            for (int i = 0; i < ((Float[][]) obj).length; i++) {

                System.out.print(Arrays.<Float>asList((Float[]) ((Float[][]) obj)[i]));

            }
            return;
        }

        if (obj instanceof Integer[]) {
            obj = Arrays.<Integer>asList((Integer[]) obj);
        }

        if (obj instanceof String[]) {
            obj = Arrays.<String>asList((String[]) obj);
        }

        if (obj instanceof Object[]) {
            obj = Arrays.<Object>asList((Object[]) obj);
        }

        System.out.print(obj);

    }

    public static void matrix(Integer[][] matrix) {

        for (Integer[] vector : matrix) {

            System.out.print("|");
            for(Integer el : vector) {

                System.out.print(el + "\t");

            }
            System.out.println("|");

        }

    }

}
