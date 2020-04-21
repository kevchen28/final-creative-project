package main
import scala.io.Source
import scala.util.Random

object typingHelpers{
  def getSentence(fname: String = "resources/sentences.txt") : List[String] = {
    def getFileLength(fname: String = "resources/sentences.txt") : Int = { // How many lines are in file
      Source.fromFile(fname).getLines.length
    }
    val index = scala.util.Random.nextInt(getFileLength(fname)+1)
    val lines = Source.fromFile(fname).getLines()
    val line = lines drop (index - 1)
    line.next().split(" ").toList
  }

  def randomizeSentence(sentence: List[String]) : List[String] = {
    Random.shuffle(sentence)
  }
}
