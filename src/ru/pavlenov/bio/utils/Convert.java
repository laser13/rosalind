package ru.pavlenov.bio.utils;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by semen on 22.11.13.
 */
public class Convert {

    public static class To {

        FASTAFileReaderImpl fasta;
        String string;
        List<Integer> integerArrayList;
        List<String> stringArrayList;

        public To(String val) {
            string = val;
        }

        public To(List<Integer> val) {
            integerArrayList = val;
        }

        public To(FASTAFileReaderImpl fasta) {
            this.fasta = fasta;
        }

        public ArrayList<String> toStringList() throws IOException {
            ArrayList<String> result = new ArrayList<>();
            FASTAElementIterator it = fasta.getIterator();
            while (it.hasNext()) {
                result.add(it.next().getSequence());
            }
            return result;
        }

        public Integer[] toIntArray(String delimeter) {
            String[] tmp = string.split(delimeter);
            Integer[] result = new Integer[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                result[i] = Integer.valueOf(tmp[i]);
            }
            return result;
        }

        public Float[] toFloatArray(String delimeter) {
            String[] tmp = string.split(delimeter);
            Float[] result = new Float[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                result[i] = Float.valueOf(tmp[i]);
            }
            return result;
        }

        public Float[][] toFloatMatrix(String delimeter1, String delimeter2) {
            String[] tmp = string.split(delimeter1);
            Float[][] result = new Float[tmp.length][tmp[0].split(delimeter2).length];
            for (int i = 0; i < tmp.length; i++) {
                String[] tmp2 = tmp[i].split(delimeter2);
                for (int j = 0; j < tmp2.length; j++) {
                    result[i][j] = Float.valueOf(tmp2[j]);
                }
            }
            return result;
        }

    }

    public static To from(String val) {
        return new To(val);
    }

    public static To from(List<Integer> val) {
        return new To(val);
    }

    public static To from(FASTAFileReaderImpl fasta) {
        return new To(fasta);
    }

}
