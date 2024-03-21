package com.swarren.amber

import com.swarren.amber.Utils.{countIndividualWords, getJsonList}

import java.nio.file.{Files, Paths}

object Main extends App {

  val filename: String = args.lift(1).getOrElse("./src/main/resources/words.json")
  println(s"Using source data from '$filename'.")

  val jsonStr: String = new String(Files.readAllBytes(Paths.get(filename)))

  val wordsSeq: Seq[Datum]  = getJsonList(jsonStr)
  val individualWordsCounted: Seq[Metrics] = countIndividualWords(wordsSeq)


  println("\n\nUnordered Distinct Words with Length:")
  individualWordsCounted
    .foreach(println)

  val wordsWithOddLength = individualWordsCounted
    .filter(_.len % 2 == 1)

  println("\n\nUnordered-Odd-Length Distinct Words with Length:")
  wordsWithOddLength
    .foreach(println)

  println("\n\nOrdered-Odd-Length Distinct Words with Length:")
  wordsWithOddLength
    .sortWith(_.len < _.len)
    .foreach(println)
}
