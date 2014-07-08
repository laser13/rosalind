package ru.pavlenov.scala.libs.peptide

/**
 * ⓭ + 44
 * Какой сам? by Pavlenov Semen 07.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
class AminoAcid {

}

object AminoAcid {

  val aa = Array(
    ('A', "Ala", "Alanine",       "C3H5NO",   7103711,   Array("GCA", "GCU", "GCC", "GCG")),
    ('C', "Cys", "Cysteine",      "C3H5NOS",  10300919,  Array("UGC", "UGU")),
    ('D', "Asp", "Aspartic acid", "C4H5NO3",  11502694,  Array("GAU", "GAC")),
    ('E', "Glu", "Glutamic acid", "C5H7NO3",  12904259,  Array("GAA", "GAG")),
    ('F', "Phe", "Phenylalanine", "C9H9NO",   14706841,  Array("UUC", "UUU")),
    ('G', "Gly", "Glycine",       "C2H3NO",   5702146,   Array("GGA", "GGU", "GGC", "GGG")),
    ('H', "His", "Histidine",     "C6H7N3O",  13705891,  Array("GAU", "GAC")),
    ('I', "Ile", "Isoleucine",    "C6H11NO",  11308406,  Array("AUU", "AUC", "AUA")),
    ('K', "Lys", "Lysine",        "C6H12N2O", 12809496,  Array("AAA", "AAG")),
    ('L', "Leu", "Leucine",       "C6H11NO",  11308406,  Array("UUG", "UUA", "CUA", "CUU", "CUC", "CUG")),
    ('M', "Met", "Methionine",    "C5H9NOS",  13104049,  Array("AUG")),
    ('N', "Asn", "Asparagine",    "C4H6N2O2", 11404293,  Array("AAC", "AAU")),
    ('P', "Pro", "Proline",       "C5H7NO",   9705276,   Array("CCA", "CCU", "CCC", "CCG")),
    ('Q', "Gln", "Glutamine",     "C5H8N2O",  12805858,  Array("CAA", "CAG")),
    ('R', "Arg", "Arginine",      "C6H12N4O", 15610111,  Array("AGA", "AGG", "CGA", "CGU", "CGC", "CGG")),
    ('S', "Ser", "Serine",        "C3H5NO2",  8703203,   Array("AGU", "AGC", "UCU", "UCC", "UCA", "UCG")),
    ('T', "Thr", "Threonine",     "C4H7NO2",  10104768,  Array("ACA", "ACU", "ACC", "ACG")),
    ('V', "Val", "Valine",        "C5H9NO",   9906841,   Array("GUA", "GUU", "GUC", "GUG")),
    ('W', "Trp", "Tryptophan",    "C11H10N20",18607931,  Array("UGG")),
    ('Y', "Tyr", "Tyrosine",      "C9H9NO2",  16306333,  Array("UAC", "UAU")),
    ('*', "XXX", "Stop",          null,       0,          Array("UGA", "UAG", "UAA"))
  )

  def getCharMass = aa.map(el => (el._1, el._5))

  def getMassChar = aa.map(el => (el._5, el._1))

}
