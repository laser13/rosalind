package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.bio.amino.Peptide

import scala.collection.mutable

/**
 * ⓭ + 10
 * Какой сам? by Pavlenov Semen 05.07.14.
 * Inferring mRNA from Protein
 * http://rosalind.info/problems/mrna/
 *
 * Given:
 * A protein string of length at most 1000 aa.
 * Return:
 * The total number of different RNA strings from which the protein could have been translated,
 * modulo 1,000,000. (Don't neglect the importance of the stop codon in protein translation.)
 */

object Mrna {

  def start() {

    println("Start: Inferring mRNA from Protein = [http://rosalind.info/problems/mrna/]")
    println("==========================")

    val base = 1000000
    val codons = "UUU F\nCUU L\nAUU I\nGUU V\nUUC F\nCUC L\nAUC I\nGUC V\nUUA L\nCUA L\nAUA I\nGUA V\nUUG L\nCUG L\nAUG M\nGUG V\nUCU S\nCCU P\nACU T\nGCU A\nUCC S\nCCC P\nACC T\nGCC A\nUCA S\nCCA P\nACA T\nGCA A\nUCG S\nCCG P\nACG T\nGCG A\nUAU Y\nCAU H\nAAU N\nGAU D\nUAC Y\nCAC H\nAAC N\nGAC D\nUAA #\nCAA Q\nAAA K\nGAA E\nUAG #\nCAG Q\nAAG K\nGAG E\nUGU C\nCGU R\nAGU S\nGGU G\nUGC C\nCGC R\nAGC S\nGGC G\nUGA #\nCGA R\nAGA R\nGGA G\nUGG W\nCGG R\nAGG R\nGGG G" split "\\n" map (s => s split " ") map (a => (a(1), a(0)))

    println(codons(0))
    println(codons.length)

    var map: mutable.Map[Char, Int] = mutable.Map().withDefaultValue(0)

    codons.foreach(el => {
      val c = el._1.charAt(0)
      if (!map.contains(c)) {
        map += (c -> 1)
      }
      else {
        map(c) += 1
      }
    })

    println(map)


    val text = "MVKRCASWWGVSMGFSYWLDKFMLPKQDNGVAFVPCQMMYQRLWNQAGMVCYAGFRFNTPKAHTWKAKKLQKYITPEIRTEIESYCYIEDRDVMTWAMHSGQQYSIDAYWDEWGELPGSRCEGQETSDFDHMHLFRWQLMTEILDCGESKKDYCDQWINLEQIYHMYNTLFVHRFKNKNGYSEWPDLTECKCQPLRPRYLAMPRYVAPSTHGRGMWNYTNNTYTGKENHLNWHQVCGQNVRAAYNCIWDVTAGDSTEFDHFFVRQESFATMSMIWRHCGYSIFWFKEHNFDLPYCGWWQPFGHTSLYGLGFGFLHVDLGAIVCDEHFEHKLYNTFMTYRPENVFFQWCMMLYYFDHWYHDMFGPAGWQMCYQDIYTMGEWGFYDGCFLYLCYSDTCGFFHQWIPPQCQELQTWTLYMNCHWTLRNHCLQPTMKLSVDWLPDNSDTIDPFQSRTTQVDKPWKFEVMVSHCRMWVDRIWCMPMCDAWYERCARCNHQTGIVMPFLIIHIFHTKAHEVRGVVIIQPVTWTIAFYVSNDCAGQASHHFGCGYDKHGKLYQQHERNTFGCNSLCPRYCYVKVPTFCTWTDQHRSPEWRNAMGSEPEAEGFVPGGPENLVCPSSQSNDDLCSEKNDMLDQQRSESNPTQGIPDMSLTEGKLRKCGNHETEWPRSCHAPKYLNALVEWRENHDQDTPYTECRGDGVFMFHMAAVYMTMIRYGWMFQIGWALDRNPFKMPGFTPIKEKNYCRSDMWHGFFIICNRMYGAICHVYGRLHGYLMTEKQKHFFEEKCKDNAFNHQYGRFDGVNVIHEKSTWDLVDIDRKCSHDETPIQCIMIIQMGHVWWHLGPCQNTKCQNGQQLDYQVQPQDWTNIMKTQSLPILYNFVNIKGRSMLRIKPGEWAFGIVWKVWWNHLHNLEYPPMEMGSSSVHWEKPMHKRQFKWVEWEITYIIPWMKAGDQCADIHRTQMYHYHEKYPIANEDENSLRVLVFKLSSWTQKWSFMMI"
    val peptide = new Peptide(text)

    var r: Int = 3
    text.toCharArray.foreach(c => {
      r *= map(c)
      if (r > base) r %= base
    })

    println(peptide.howMany(1000000))
    println(r)

  }

}