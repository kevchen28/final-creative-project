package main

object typingGame {
  def newSentence(): Unit = {
    sentence = typingHelpers.getSentence()
    wordList = typingHelpers.randomizeSentence(sentence)
    wordBank = printSentence(wordList)
    sentenceString = printSentence(sentence)
  }

  def newSentenceComp(): Unit = {
    sentenceComp = typingHelpers.getSentence()
    wordListComp = typingHelpers.randomizeSentence(sentenceComp)
    wordBankComp = printSentence(wordListComp)
    sentenceStringComp = printSentence(sentenceComp)
  }

  def checkGuess(guess: String): String = {
    def remove(str: String, lst: List[String]) = lst diff List(str)
    if (guess.toLowerCase().equals(sentence(0).toLowerCase())) {
      val g = sentence(0)
      sentence = sentence.slice(1, sentence.size)
      wordList = remove(g, wordList)
      wordBank = printSentence(wordList)
      userPoints += 1
      g + " "
    } else {
      userPoints -= 1
      ""
    }
  }

  def checkGuessComp(guess: String): String = {
    if (guess.toLowerCase().equals(sentenceComp(0).toLowerCase())) {
      def remove(str: String, lst: List[String]) = lst diff List(str)
      val g = sentenceComp(0)
      sentenceComp = sentenceComp.slice(1, sentenceComp.size)
      wordListComp = remove(g, wordListComp)
      wordBankComp = printSentence(wordListComp)
      compPoints += 1
      g + " "
    } else ""
  }

  def printSentence(s: List[String]): String = {
    var str = ""
    for (i <- s) {
      str += i + " "
    }
    str
  }

  var sentence = typingHelpers.getSentence()
  var wordList = typingHelpers.randomizeSentence(sentence)
  var sentenceString = printSentence(sentence)
  var wordBank = printSentence(wordList)
  var userPoints = 0
  var sentenceComp = typingHelpers.getSentence()
  var wordListComp = typingHelpers.randomizeSentence(sentence)
  var sentenceStringComp = printSentence(sentence)
  var wordBankComp = printSentence(wordList)
  var compPoints = 0
}
