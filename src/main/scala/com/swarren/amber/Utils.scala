package com.swarren.amber

import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.{Formats, ShortTypeHints}


object Utils {
  /**
   *
   * @param jsonStr - a string of JSON in the form of List[Datum]
   * @return a sequence of the data in JSON (json4s) representation
   */
  def getJsonList(jsonStr: String): Seq[Datum] = {
    implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Datum])))
    parse(jsonStr).extract[List[Datum]]
  }

  /**
   *
   * @param wordsSeq - Seq of Datum
   * @return a count of word occurrences and lengths
   */
  def countIndividualWords(wordsSeq: Seq[Datum]): Seq[Metrics] = wordsSeq
    .flatMap(_.words.toLowerCase.split(" "))
    .groupBy(identity)
    .toList
    .map { case (word, list) => Metrics(word, word.length, list.length)}

  /**
   *
   * @param wordsSeq - Seq of Datum
   * @return a count of letter occurrences
   */
  def countIndividualLetters(wordsSeq: Seq[Datum]): Map[Char, Int] = wordsSeq
    .flatMap(_.words.toLowerCase.split(" ").flatMap((x: String) => x.toList))
    .groupBy(identity)
    .toList
    .map { case (letter, list) => letter -> list.length }
    .toMap

}