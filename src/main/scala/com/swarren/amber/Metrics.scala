package com.swarren.amber

case class Metrics(word: String, len: Int, count: Int) {
   override def toString = s"$word has a length of $len letter(s) and appears $count time(s)."
}
