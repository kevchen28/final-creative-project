/* Item.scala
 * abstract class Item with case classes extending it
 */

package main

abstract class Item
case class Coin(value: Int, face: String, color: String) extends Item
case class Phone(version: Int, name: String, color: String) extends Item
case class Car(year: Int, company: String, color: String) extends Item
case class Book(year: Int, author: String, color: String) extends Item
