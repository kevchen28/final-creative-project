/* patternGame.scala
 * Contains functions and members used for creativePatternGUI.scala
 * functions: writeCaseClass(), pickCaseClass(), pickCompCaseClass(), pickItem()
 * members: classList, primaryClass, secondaryClass, compPrimaryClass, compSecondaryClass
 */

package main
import scala.io.Source
import scala.util.Random

object patternGame {
  def writeCaseClass(item: Item): String = { //outputs a string based off of the case class picked
    item match {
      case Coin(value: Int, face: String, color: String) =>
        "(" + value + ", " + face + ", " + color + ")"
      case Phone(version: Int, name: String, color: String) =>
        "(" + version + ", " + name + ", " + color + ")"
      case Car(year: Int, name: String, color: String) =>
        "(" + year + ", " + name + ", " + color + ")"
      case Book(year: Int, author: String, color: String) =>
        "(" + year + ", " + author + ", " + color + ")"
    }
  }

  def pickCaseClass(): Unit = { // picks two random case classes from classList
    primaryClass = classList(Random.nextInt(classList.length))
    val cl2 = classList diff List(primaryClass)
    secondaryClass = cl2(Random.nextInt(cl2.length))
  }

  def pickCompCaseClass(): Unit = { //picks two random case classes from classList
    compPrimaryClass = classList(Random.nextInt(classList.length))
    val cl2 = classList diff List(primaryClass)
    compSecondaryClass = cl2(Random.nextInt(cl2.length))
  }

  def pickItem(c: String): Item = { //Picks a random item from a .txt file in the resources folder
    def getLine(fname: String) : List[String] = {
      def getFileLength(fname: String) : Int = { // How many lines are in file
        Source.fromFile(fname).getLines.length
      }
      val index = scala.util.Random.nextInt(getFileLength(fname)+1)
      val lines = Source.fromFile(fname).getLines()
      val line = lines drop (index - 1)
      line.next().split(" ").toList //parses by spaces in line, returns List[String]
    }
    val item = getLine("resources/" + c + ".txt") //reads random line from <Item>.txt in resources file
    c match { //convert String into Item
      case "Coin" =>
        Coin(item(0).toInt, item(1), item(2))
      case "Phone" =>
        Phone(item(0).toInt, item(1), item(2))
      case "Car" =>
        Car(item(0).toInt, item(1), item(2))
      case "Book" =>
        Book(item(0).toInt, item(1), item(2))
    }
  }

  val classList = List("Coin", "Phone", "Car", "Book") //list of classes you can choose from
  var primaryClass = ""
  var secondaryClass = ""
  var compPrimaryClass = ""
  var compSecondaryClass = ""
}
