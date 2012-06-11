package me.bazhenov.classifier


class NaiveBayesClassifier(m: NaiveBayesModel) {

	def classify(s: String) = {
		m.classes.toList.map(c => (c, calculateProbability(c, s))).sortBy(_._2).last._1
	}

	def tokenize(s: String) = s.split(' ')

	def calculateProbability(c: String, s: String) = {
		tokenize(s).map(m.wordLogProbability(c, _)).reduceLeft(_ + _) + m.classLogProbability(c)
	}
}
