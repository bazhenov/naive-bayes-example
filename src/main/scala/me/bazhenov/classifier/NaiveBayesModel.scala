package me.bazhenov.classifier

import scala.math.log

/**
 * Модель классификатора. Включает в себя всю необходимую для классификации статистику
 *
 * @param lengths суммарные длина документов в словах по классам
 * @param docCount количество документов по класам
 * @param wordCount статистика по цитируемости слов в пределах классов
 * @param dictionarySize размер словаря обуающей выборки
 */
class NaiveBayesModel(lengths: Map[String, Int],
											docCount: Map[String, Int],
											wordCount: Map[String, Map[String, Int]],
											dictionarySize: Int) {

	/**
	 * @param c класс
	 * @param w слово
	 * @return логарифм оценки <code>P(w|c)</code> — вероятности слова в пределах класса
	 */
	def wordLogProbability(c: String, w: String) =
		log((wordCount(c).getOrElse(w, 0) + 1.0) / (lengths(c).toDouble + dictionarySize))

	/**
	 * @param c класс
	 * @return логарифм априорной вероятности класса <code>P(c)</code>
	 */
	def classLogProbability(c: String) = log(docCount(c).toDouble / docCount.values.reduceLeft(_ + _))

	/**
	 * @return множество всех классов
	 */
	def classes = docCount.keySet
}
