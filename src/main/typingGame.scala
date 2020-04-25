/* typingGame.scala
 * Contains functions and members for Typing Game
 * helper functions: newSentence(), newSentenceComp(), checkGuess(), checkGuessComp(), printSentence()
 * members: sentence, sentenceComp, wordList, wordListComp, sentenceString, sentenceStringComp, user Points, compPoints
 * uses methods from typingHelpers.scala
 */

package main

object typingGame {
  //Retrieves a new sentence for the user
  def newSentence(): Unit = {
    sentence = typingHelpers.getSentence()
    wordList = typingHelpers.randomizeSentence(sentence)
    wordBank = printSentence(wordList)
    sentenceString = printSentence(sentence)
  }
  //Retrieves a new sentence for the computer
  def newSentenceComp(): Unit = {
    sentenceComp = typingHelpers.getSentence()
    wordListComp = typingHelpers.randomizeSentence(sentenceComp)
    wordBankComp = printSentence(wordListComp)
    sentenceStringComp = printSentence(sentenceComp)
  }
  //Checks the users guess to see if there is a match, removes word from word bank if true
  def checkGuess(guess: String): String = {
    def remove(str: String, lst: List[String]) = lst diff List(str) //remove word from List
    if (guess.toLowerCase().equals(sentence(0).toLowerCase())) {
      val g = sentence(0)
      sentence = sentence.slice(1, sentence.size) //Removes first word of the sentence, as it is already guessed
      wordList = remove(g, wordList)              //Remove correct guess off the word bank
      wordBank = printSentence(wordList)
      userPoints += 1
      g + " "
    } else {
      userPoints -= 1
      ""
    }
  }

  //Checks the computer's guess to see if there is a match, removes word from word bank if true
  def checkGuessComp(guess: String): String = {
    if (guess.toLowerCase().equals(sentenceComp(0).toLowerCase())) {
      def remove(str: String, lst: List[String]) = lst diff List(str)
      val g = sentenceComp(0)
      sentenceComp = sentenceComp.slice(1, sentenceComp.size) //Removes first word of the sentence, as it is already guessed
      wordListComp = remove(g, wordListComp)  //Remove correct guess off the word bank
      wordBankComp = printSentence(wordListComp)
      compPoints += 1
      g + " "
    } else ""
  }

  //Returns a string from a list of words
  def printSentence(s: List[String]): String = {
    var str = ""
    for (i <- s) {
      str += i + " "
    }
    str
  }

  var sentence = typingHelpers.getSentence()    //user's sentence, List[String]
  var wordList = typingHelpers.randomizeSentence(sentence)  //user's list of words left in randomized order, List[String]
  var sentenceString = printSentence(sentence)  // String of remaining sentence
  var wordBank = printSentence(wordList)        // String containing list of words left in random order
  var userPoints = 0
  var sentenceComp = typingHelpers.getSentence() //computer's sentence, List[String]
  var wordListComp = typingHelpers.randomizeSentence(sentence) //user's list of words left in randomized order, List[String]
  var sentenceStringComp = printSentence(sentence) //String of remaining sentence
  var wordBankComp = printSentence(wordList) // String containing list of words left in random order
  var compPoints = 0
}
