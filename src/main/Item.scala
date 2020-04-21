package main

abstract class Item {
  def color: String
}
case class Coin(value: Int, face: String, color: String) extends Item
case class Phone(version: Int, name: String, color: String) extends Item
case class Car(year: Int, company: String, color: String) extends Item
case class Book(year: Int, author: String, color: String) extends Item
