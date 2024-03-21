package com.swarren.amber

import com.swarren.amber.Utils.{countIndividualLetters, countIndividualWords}

class UtilTests extends munit.FunSuite {

  val fox = "The quick brown fox jumps over the lazy dog"
  val vow = "Sphinx of black quartz judge my vow"
  val box = "Pack my box with five dozen liquor jugs"

  private val jsonStr = s"""[
                  |  {
                  |    "words": "$fox"
                  |  },
                  |  {
                  |    "words": "$vow"
                  |  },
                  |  {
                  |    "words": "$box"
                  |  }
                  |]""".stripMargin

  test("Does the JsonStr convert?") {
    val res: Seq[String] = Utils.getJsonList(jsonStr).map(_.words)
    assert(res.contains(fox))
    assert(res.contains(vow))
    assert(res.contains(box))
  }

  test("Does the Json convert to word counts / lengths?") {
    val expected: Set[Metrics] = s"$fox $vow $box"
      .split(" ")
      .map(_.toLowerCase)
      .groupBy(identity)
      .toList
      .map{ case (w, l) => Metrics(w, w.length, l.length)}
      .toSet
    val metrics: Set[Metrics] =
      countIndividualWords(Seq(Datum(fox),Datum(vow),Datum(box))).toSet

    assert(metrics.equals(expected))
  }


  test("Does the Json convert to letter counts?") {
    val expected: Map[Char, Int] = s"$fox $vow $box"
      .toLowerCase
      .toList
      .filter(_ != ' ' )
      .groupBy(identity)
      .map { case (letter, clutch) => letter -> clutch.length }

    val results: Map[Char, Int] =
      countIndividualLetters(Seq(Datum(fox), Datum(vow), Datum(box)))

    expected.keys.foreach(key => assert(expected(key) == results(key)))
    assert(results.size == expected.size)
  }

}

