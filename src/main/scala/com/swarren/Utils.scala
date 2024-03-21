package com.swarren

import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.{Formats, ShortTypeHints}


object Utils {
  def getJsonList(jsonStr: String): Seq[Datum] = {
    implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Datum])))
    parse(jsonStr).extract[List[Datum]]
  }

  def countIndividualWords(wordsSeq: Seq[Datum]): Seq[Metrics] = wordsSeq
    .flatMap(_.words.toLowerCase.split(" "))
    .groupBy(identity)
    .toList
    .map { case (word, list) =>
      Metrics(word, word.length, list.length)
    }

}
