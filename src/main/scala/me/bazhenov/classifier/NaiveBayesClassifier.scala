package me.bazhenov.classifier

/**
 * Алгоритм непосредственно классификации
 * @param m статистическая модель классификатора
 */
class NaiveBayesClassifier(m: NaiveBayesModel) {

	def classify(s: String) = {
		m.classes.toList.map(c => (c, calculateProbability(c, s))).sortBy(_._2).last._1
	}

	def tokenize(s: String) = s.split(' ')

	/**
	 * Рассчитывает оценку вероятности документа в пределах класса
	 * @param c класс
	 * @param s текст документа
	 * @return оценка <code>P(c|d)</code>
	 */
	def calculateProbability(c: String, s: String) = {
		tokenize(s).map(m.wordLogProbability(c, _)).reduceLeft(_ + _) + m.classLogProbability(c)
	}
}
