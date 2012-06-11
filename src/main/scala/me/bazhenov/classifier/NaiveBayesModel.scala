package me.bazhenov.classifier

import scala.math.log

class NaiveBayesModel(lengths: Map[String, Int],
											docCount: Map[String, Int],
											wordCount: Map[String, Map[String, Int]],
											dictionarySize: Int) {

	def wordLogProbability(c: String, w: String) = log((wordCount(c).getOrElse(w, 0) + 1.0) / (lengths(c).toDouble + dictionarySize))

	def classLogProbability(c: String) = log(docCount(c).toDouble / docCount.values.reduceLeft(_ + _))

	def classes = docCount.keySet
}
