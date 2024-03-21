package com.swarren.amber

import com.swarren.amber.Utils.{countIndividualLetters, countIndividualWords, getJsonList}

import java.nio.file.{Files, Paths}

object Main extends App {

  val filename: String = args.lift(1).getOrElse("./src/main/resources/words.json")
  println(s"\n\nUsing source data from '$filename'.")

  val jsonStr: String = new String(Files.readAllBytes(Paths.get(filename)))

  val wordsSeq: Seq[Datum]  = getJsonList(jsonStr)
  val individualWordsCounted: Seq[Metrics] = countIndividualWords(wordsSeq)

  println(s"\nTotal number of words is ${individualWordsCounted.foldLeft(0)((sum, word) => sum + word.count)}")
  println(s"\nTotal number of characters is ${individualWordsCounted.foldLeft(0)((sum, word) => sum + word.len)}")
  println(s"\nTotal number of letters appearances is:")
  val individualLettersCounted = countIndividualLetters(wordsSeq)
  individualLettersCounted
    .keySet
    .toList
    .sorted
    .foreach(key => println(s"Letter '$key' appears ${individualLettersCounted(key)} times."))

  println(s"\n\nUnordered Distinct Words with Length (${individualWordsCounted.length} words):")
  individualWordsCounted.foreach(println)

  val wordsWithOddLength = individualWordsCounted.filter(_.len % 2 == 1)

  println(s"\n\nUnordered-Odd-Length Distinct Words with Length (${wordsWithOddLength.length} words):")
  wordsWithOddLength.foreach(println)

  println(s"\n\nOrdered-Odd-Length Distinct Words with Length (${wordsWithOddLength.length} words):")
  wordsWithOddLength
    .sortWith(_.len < _.len)
    .foreach(println)
}
