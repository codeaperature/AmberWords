package com.swarren.amber

import Utils.{countIndividualWords, getJsonList}
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization

import java.nio.file.{Files, Paths}

object Main extends App {

  implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Datum])))

  val filename: String = args.lift(1).getOrElse("./src/main/resources/words.json")
  println(s"Using source data from '$filename'.")

  val jsonStr = new String(Files.readAllBytes(Paths.get(filename)))
//  val wordsSeq: Seq[Datum] = parse(jsonStr).extract[List[Datum]]
//  val individualWordsCounted: Seq[Metrics] = wordsSeq
//    .flatMap(_.words.toLowerCase.split(" "))
//    .groupBy(identity)
//    .toList
//    .map { case (word, list) =>
//      Metrics(word, word.length, list.length)
//    }
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
