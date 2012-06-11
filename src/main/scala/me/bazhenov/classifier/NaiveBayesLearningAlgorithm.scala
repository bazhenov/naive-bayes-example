package me.bazhenov.classifier

class NaiveBayesLearningAlgorithm {

	private var examples: List[(String, String)] = List()

	private val tokenize = (v: String) => v.split(' ')
	private val tokenizeTuple = (v: (String, String)) => tokenize(v._1)
	private val calculateWords = (l: List[(String, String)]) => l.map(tokenizeTuple(_).length).reduceLeft(_ + _)

	def addExample(ex: String, cl: String) {
		examples = (ex, cl) :: examples
	}

	def dictionary = examples.map(tokenizeTuple).flatten.toSet

	def model = {
		val docsByClass = examples.groupBy(_._2)
		val lengths = docsByClass.mapValues(calculateWords)
		val docCounts = docsByClass.mapValues(_.length)
		val wordsCount = docsByClass.mapValues(_.map(tokenizeTuple).flatten.groupBy(x => x).mapValues(_.length))
		val dictionary = examples.map(_._1).map(tokenize).flatten.toSet

		new NaiveBayesModel(lengths, docCounts, wordsCount, dictionary.size)
	}

	def classifier = new NaiveBayesClassifier(model)
}
