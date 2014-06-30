package ru.pavlenov.bio.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 18.11.13
 * Time: 11:56
 * <p>
 * E = mc^2
 */
public class File {

    private static BufferedWriter logWriter;

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public static void writeFile(String path, String text) throws IOException {
        BufferedWriter logWriter = new BufferedWriter(new FileWriter(path, true));
        logWriter.write(text);
        logWriter.flush();
    }

}
