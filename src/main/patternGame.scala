package main
import scala.util.Random

object patternGame {
  def writeCaseClass(item: Item): String = {
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

  def pickCaseClass(): Unit = {
    primaryClass = classList(Random.nextInt(classList.length - 1))
    val cl2 = classList diff List(primaryClass)
    secondaryClass = cl2(Random.nextInt(cl2.length - 1))
  }

  def pickCompCaseClass(): Unit = {
    compPrimaryClass = classList(Random.nextInt(classList.length - 1))
    val cl2 = classList diff List(primaryClass)
    compSecondaryClass = cl2(Random.nextInt(cl2.length - 1))
  }

  def pickItem(c: String): Item = {
    var x = 0
    c match {
      case "Coin" =>
        x = Random.nextInt(coinList.length - 1)
        coinList(x)
      case "Phone" =>
        x = Random.nextInt(phoneList.length - 1)
        phoneList(x)
      case "Car" =>
        x = Random.nextInt(carList.length - 1)
        carList(x)
      case "Book" =>
        x = Random.nextInt(bookList.length - 1)
        bookList(x)
    }
  }

  val classList = List("Coin", "Phone", "Car", "Book")
  var primaryClass = ""
  var secondaryClass = ""
  var compPrimaryClass = ""
  var compSecondaryClass = ""

  val coinList = List(Coin(1, "Lincoln", "copper"), Coin(50, "Kennedy", "silver"), Coin(5, "Jefferson", "silver"), Coin(10, "Roosevelt", "silver"), Coin(25, "Washington", "silver"))
  val phoneList = List(Phone(10, "Samsung", "blue"), Phone(9, "Apple", "white"), Phone(10, "Blackberry", "black"), Phone(8, "Motorola", "purple"))
  val carList = List(Car(2002, "Toyota", "red"), Car(2019, "Tesla", "black"), Car(2009, "Honda", "blue"), Car(2007, "Mazda", "green"))
  val bookList = List(Book(1925, "Fitzgerald", "white"), Book(1997, "Rowling", "red"), Book(1949, "Orwell", "white"), Book(1954, "Tolkein", "blue"))
}
