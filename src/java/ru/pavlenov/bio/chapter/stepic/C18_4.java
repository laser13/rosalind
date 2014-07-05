package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.*;
import ru.pavlenov.bio.utils.File;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 18.11.13
 * Time: 11:48
 * <p>
 * E = mc^2
 */
public class C18_4 {

    public static void start() throws IOException {
        String dnaText = File.readFile("/home/semen/www/IdeaProjects/bio/genome/B_brevis.txt", StandardCharsets.UTF_8);

        System.out.println(dnaText.length());

//        String peptideText = "VKLFPWFNQY";
        String peptideText = "MA";

        DNA dna = new DNA(dnaText);

        System.out.println("create DNA");

        RNA rna = dna.transcribe();

        System.out.println("transcribe: " + rna.get().length());

//        System.out.println(rna);
//        System.out.println(rna.getReverse());

        ArrayList<String> area = rna.translate().findArea(peptideText);

//        for (String a : area) {
//            System.out.println(RNA.toDNA(a));
//        }
        System.out.println(area.size());

    }

}
