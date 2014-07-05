package ru.pavlenov.bio.utils;

import com.google.common.base.Joiner;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.chapter.rosalind.string_algoritm.Kmp;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

    public static FASTAFileReaderImpl readFasta(Class clazz) throws IOException {

        String userDir = System.getProperty("user.dir");
        String packageDir = Joiner.on("/").join(clazz.getName().split("\\."));
        String data = File.readFile(userDir + "/src/" + packageDir + ".data", Charset.defaultCharset());
        return new FASTAFileReaderImpl(new StringReader(data));

    }

}
