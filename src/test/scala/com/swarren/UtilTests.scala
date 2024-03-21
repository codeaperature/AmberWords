package com.swarren

import com.swarren.Utils.countIndividualWords

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

  test("Does the Json convert to counts?") {
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


}

