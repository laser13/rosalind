package ru.pavlenov.bio.amino;

import ru.pavlenov.bio.utils.Sort;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.11.13 19:06
 */
public class Spectra {

    public static class SeqItem {

        private String name = "";
        private ArrayList<Integer> mass;
        private Integer sum = 0;
        private Integer[] tSpectrum;
        private Integer scope;

        public SeqItem(ArrayList<Integer> mass) {

            this.mass = mass;
            for (int m : mass) {
                sum += m;
                name += m + "-";
            }
            name = name.substring(0, name.length()-1);
            tSpectrum = Peptide.cyclospectrum(mass.toArray(new Integer[mass.size()]));

        }

        public Integer getScope() {
            return scope;
        }

        public void setScope(Integer[] eSpectrum) {
            scope = Peptide.scope(tSpectrum, eSpectrum);
        }

        public String getName() {
            return name;
        }

        public ArrayList<Integer> getMass() {
            return mass;
        }

        public Integer[] getSpectrum() {
            return tSpectrum;
        }

        public String toString() {
            return "{ " + name + " | " + scope + " }";
        }

        public Integer getSum() {
            return sum;
        }
    }

    public static ArrayList<SeqItem> cut(ArrayList<SeqItem> list, int count) {

        ArrayList<SeqItem> result = new ArrayList<>();

        list.sort((o1, o2) -> o2.getScope() - o1.getScope());

//        System.out.println("--------------------------------------------------------------------------------");
//        System.out.println(list);


        SeqItem lastSeqItem = null;
        for (SeqItem seqItem : list) {

            if (result.size() < count-1) {
                result.add(seqItem);
            }
            else if (result.size() == count-1) {
                lastSeqItem = seqItem;
                result.add(seqItem);
//                System.out.println("lastSeqItem: " + lastSeqItem.getScope());
            }
            else {
//                System.out.println("lastSeqItem: " + lastSeqItem.getScope() + " | " + seqItem.getScope());
                if (lastSeqItem != null && seqItem.getScope().equals(lastSeqItem.getScope())) {
                    result.add(seqItem);
                }
                else {
                    break;
                }
            }

        }

        return result;

    }

    public static ArrayList<SeqItem> expand(ArrayList<SeqItem> list) {

        Integer[] mass = Codon.getMass();
        ArrayList<SeqItem> result = new ArrayList<>();

        if (list.size() == 1 && list.get(0).getSum() == 0) {
            for (Integer m : mass) {
                ArrayList<Integer> mass0 = new ArrayList<>();
                mass0.add(m);
                result.add(new SeqItem(mass0));
            }
        }
        else {

            for (SeqItem s : list) {
                for (Integer m : mass) {
                    ArrayList<Integer> mass0 = new ArrayList<>();
                    mass0.addAll(s.getMass());
                    mass0.add(m);
                    result.add(new SeqItem(mass0));
                }
            }
        }
        return result;
    }

    public static ArrayList<SeqItem> expand(ArrayList<SeqItem> list, Integer[] mass) {

        ArrayList<SeqItem> result = new ArrayList<>();

        if (list.size() == 1 && list.get(0).getSum() == 0) {
            for (Integer m : mass) {
                ArrayList<Integer> mass0 = new ArrayList<>();
                mass0.add(m);
                result.add(new SeqItem(mass0));
            }
        }
        else {

            for (SeqItem s : list) {
                for (Integer m : mass) {
                    ArrayList<Integer> mass0 = new ArrayList<>();
                    mass0.addAll(s.getMass());
                    mass0.add(m);
                    result.add(new SeqItem(mass0));
                }
            }
        }
        return result;
    }

    public static Integer[] convolution(Integer[] spectrum, int m) {

        HashMap<Integer, Integer> t = new HashMap<>();

        for (Integer d1 : spectrum) {
            for (Integer d2 : spectrum) {
                int d = d1 - d2;
                if (d >= 57 && d <= 200) if (t.containsKey(d)) {
                    Integer v = t.get(d);
                    v++;
                    t.put(d, v);
                } else {
                    t.put(d, 1);
                }
            }
        }

        Map sortedMap = Sort.byValue(t, -1);
        ArrayList<Integer> newMass = new ArrayList<>();
        Integer lastMass = 0;
        for (Object o : sortedMap.entrySet()) {
            Map.Entry<Integer, Integer> thisEntry = (Map.Entry<Integer, Integer>) o;
            Integer key = thisEntry.getKey();
            Integer value = thisEntry.getValue();

            if (newMass.size() < m-1) {
                newMass.add(key);
            }
            else if (newMass.size() == m-1) {
                lastMass = value;
                newMass.add(key);
            }
            else {
                if (lastMass.equals(value)) {
                    newMass.add(key);
                }
                else {
                    break;
                }
            }

        }
        
        return newMass.toArray(new Integer[newMass.size()]);

    }

    public static void convolutionCyclopeptideSequencing(Integer[] eSpectrum, int n, int m) {

        ArrayList<SeqItem> list = new ArrayList<>();
        ArrayList<Integer> mass0 = new ArrayList<>();
        mass0.add(0);
        list.add(new SeqItem(mass0));


        Integer[] newMass = convolution(eSpectrum, m);

        System.out.println("**********************************************");
        System.out.println("START");
        System.out.println("**********************************************");

        int i = 0;
        int parentMass = eSpectrum[eSpectrum.length-1];
        SeqItem leader = new SeqItem(mass0);
        ArrayList<SeqItem> leaderList = new ArrayList<>();

        while (!list.isEmpty()) {
            System.out.print("LOOP: " + i + " ");
            list = expand(list, newMass);

            System.out.print(", size1: " + list.size());

            ArrayList<SeqItem> removeKey = new ArrayList<>();

            for (SeqItem seqItem : list) {

                seqItem.setScope(eSpectrum);
                int mass = seqItem.getSum();

                if (mass == parentMass) {

                    leaderList.add(seqItem);

                    if (Peptide.scope(leader.tSpectrum, eSpectrum) < Peptide.scope(seqItem.tSpectrum, eSpectrum)) {
                        leader = seqItem;
                    }
                }
                else if (mass > parentMass) {
                    removeKey.add(seqItem);
                }

            }

            for (SeqItem key : removeKey) {
                list.remove(key);
            }

            list = cut(list, n);

            System.out.println(", size2: " + list.size());
            i++;

        }

        System.out.println(leaderList);
        System.out.println(leader);


    }

    public static void leaderCyclopeptideSequencing(Integer[] eSpectrum, int n) {

        ArrayList<SeqItem> list = new ArrayList<>();
        ArrayList<Integer> mass0 = new ArrayList<>();
        mass0.add(0);
        list.add(new SeqItem(mass0));

        System.out.println(eSpectrum.length);
        System.out.println(Arrays.asList(eSpectrum));
        System.out.println("**********************************************");
        System.out.println("START");
        System.out.println("**********************************************");

//        ArrayList<SeqItem> result = new ArrayList<>();

        ArrayList<Integer> testMass = new ArrayList<>();
        Collections.addAll(testMass, 156,71,113,114,131,156,113,101,129,128,128,114,128,103,97,131,131,113,131,113,128,115);
        Peptide testPeptide = new Peptide(testMass);
        Integer[] testCyclo = testPeptide.cyclospectrum();

        System.out.println(Arrays.asList(testCyclo));
        System.out.println(testCyclo.length);
        System.out.println(testPeptide.scope(eSpectrum));
        System.out.println(testPeptide.toMassString());
        System.out.println("---------------------------------------------------------------------");


        int i = 0;
        int parentMass = eSpectrum[eSpectrum.length-1];
        Peptide leader = new Peptide();

//        System.out.println(Arrays.asList(leader.cyclospectrum()));

        while (!list.isEmpty()) {
//            System.out.println("============================================================================================================================");
            System.out.print("LOOP: " + i + " ");
            list = expand(list);

            System.out.print(", size1: " + list.size());

            ArrayList<SeqItem> removeKey = new ArrayList<>();

            for (SeqItem seqItem : list) {

                seqItem.setScope(eSpectrum);
                int mass = seqItem.getSum();

                if (mass == parentMass) {
                    if (leader.scope(eSpectrum) < Peptide.scope(seqItem.tSpectrum, eSpectrum)) {
                        leader = new Peptide(seqItem.getMass());
                    }
                }
                else if (mass > parentMass) {
                    removeKey.add(seqItem);
                }

            }

            for (SeqItem key : removeKey) {
                list.remove(key);
            }

            list = cut(list, n);

            System.out.println(", size2: " + list.size());

//            System.out.println("============================================================================================================================");
            i++;

        }

        System.out.println(leader.toString().length());
        System.out.println(leader.toMassString());

    }

    public static void cyclopeptideSequencing(Integer[] eSpectrum) {

        ArrayList<SeqItem> list = new ArrayList<>();
        for (Integer m : Codon.getMass()) {
            ArrayList<Integer> mass0 = new ArrayList<>();
            mass0.add(m);
            list.add(new SeqItem(mass0));
        }

        System.out.println(list);
        System.out.println(Arrays.asList(eSpectrum));
        System.out.println("**********************************************");

        ArrayList<SeqItem> result = new ArrayList<>();

        int i = 0;

        while (!list.isEmpty()) {

            ArrayList<SeqItem> removeKey = new ArrayList<>();
            ArrayList<SeqItem> successKey = new ArrayList<>();

            for (SeqItem seqItem : list) {


                if (Peptide.compare(seqItem.getSpectrum(), eSpectrum)) {
                    System.out.println(seqItem.getName());
                    System.out.println("================================");
                    successKey.add(seqItem);
                }
                else {
                    removeKey.add(seqItem);
                }

            }

            if (!successKey.isEmpty()) {
                result.clear();
                result.addAll(successKey);
            }

            for (SeqItem key : removeKey) {
                list.remove(key);
            }

            System.out.println("LOOP: " + i);
            i++;
            System.out.println(list);

//            if (i == 1) list.clear();

            list = expand(list);
            System.out.println();

        }

        System.out.println(result);



    }

}
