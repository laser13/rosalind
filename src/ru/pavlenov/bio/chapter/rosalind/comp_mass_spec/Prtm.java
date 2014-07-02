package ru.pavlenov.bio.chapter.rosalind.comp_mass_spec;

import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.utils.Dump;

/**
 * Created by laser13 on 19.04.14.
 */
public class Prtm {

    /**
     * http://rosalind.info/problems/prtm/
     * Calculating Protein Mass
     *
     * Given: A protein string P of length at most 1000 aa.
     * Return: The total weight of P. Consult the monoisotopic mass table.
     *
     */
    public static void start() {

        String data = "KSETVQVKFGRKGYNETDCDPIRTLYMNWHYDNAWCTPAQNNNFGATCITGDPLNLWEAYGSPAHVHEDNRHTFFIPRDAVKHRMYVNDKQYYHCHHGECHLSMAMLRYSRQTTACAKKHLWVIRSQTKCHHTRIWKECQDFQPSVRLCAPWGPLWKKIMANCIDLKIHNPGERTSQWIVSPAMYFPVPPSNKLISLKPGALTSEENALMKEMVGSPGWWDKQEWWESRTMSCPWQKNSDPDLVKGQRGYVYIYFHQPCNWDDYASDDQDPCAHILIAFIQDLKNIVKSTHECMVEFMTMTRHTKWFIGSLDTQFTELWNYTNWQRPDDHRWKSRAVSFQKLHNNCKQMKSVGRCEETHDIDHLDATKECTLRMETDCWDWEECLLCWEEYQVLNRPIMHHWHRYMTNEWENNRVQMAYNKISQGDPRCNDSQARTAAFIESGPHETMLMGECIRPIAFYDSDQEQNTKMNHESVYGCSDVPCGHKMNYRREKETTWDRMMFDDSEPYQRLHQFLKVMTGWNHYPYYLTNYNKHVEKTKAGFYTQNSSTVMNFWMEMQPYFNTQMQTTHMAVLEKFKITRPQWAVRFQNEPSRNQERMCILARPKFHYCHPMHKIFCQFVRPQPRYINQEHDDTQQNQEFKTDGQNKHKAGSRLMYEASGKPAIRVNAWTTIIGNHSWSMVHKVIFGNCQAAIVFVIHMVPMHHPQSATWWLMKAYCATTQHKNQHWSLCEKSPNDRGWMMAWCGVAGWIARMATHNLTFIIAMAPSYIFQYTKERRYWRMTNVPWVASKPQMMNMTWKMYLWKQWMNAEDWLCINYAWQNVAGTPLKMGVASKYEHYTQVVLDTMVNCCALSPAFACVDLNQWTSCWADPKFMTAMIYNALSGSYYWYTETPIRKGFMACAYADALSFDSPYQFLFMKAHLMELHSNMCNIQNFTMINWSASFMCGFCLRKEYIQWGRC";

        Peptide peptide = new Peptide(data);

        Dump.println(peptide.getTrueMass());

    }

}