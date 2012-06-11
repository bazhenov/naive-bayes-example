package me.bazhenov.classifier

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ClassifierSpec extends FlatSpec with ShouldMatchers {

	"Classifier" should "be able to guess classes" in {
		val c = new NaiveBayesLearningAlgorithm()
		c.addExample("предоставляю услуги бухгалтера", "SPAM")
		c.addExample("спешите купить виагру", "SPAM")
		c.addExample("надо купить молоко", "HAM")

		val bestClass = c.classifier.classify("надо купить сигареты")
		bestClass should equal("HAM")
	}
}
