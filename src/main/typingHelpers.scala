/* typingHelpers.scala
 * Contains helpers for typingGame.scala
 * functions: getSentence(), randomizeSentence()
 */

package main
import scala.io.Source
import scala.util.Random

object typingHelpers{
  // retrieves a random sentence from a text file
  def getSentence(fname: String = "resources/sentences.txt") : List[String] = {
    def getFileLength(fname: String = "resources/sentences.txt") : Int = { // How many lines are in file
      Source.fromFile(fname).getLines.length
    }
    val index = scala.util.Random.nextInt(getFileLength(fname)+1) //pick random line from file
    val lines = Source.fromFile(fname).getLines()
    val line = lines drop (index - 1)
    line.next().split(" ").toList //parses by spaces in sentence, returns List[String]
  }

  // Shuffles the order of a sentence for wordList and wordListComp
  def randomizeSentence(sentence: List[String]) : List[String] = {
    Random.shuffle(sentence)
  }
}
