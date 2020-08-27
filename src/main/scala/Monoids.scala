package monoid

import cats.Monoid
import cats.instances.int._
import cats.instances.list._
import cats.instances.map._
import cats.syntax.monoid._

object Monoids extends App {

  // combine a list of phonebooks as Maps[String, Int]
  def createPhoneBook: Unit = {

    val phoneBooks = List(
      Map(
        "Alice" -> 235,
        "Bob" -> 647
      ),
      Map(
        "Charlie" -> 372,
        "Daniel" -> 889
      ),
      Map(
        "Tina" -> 123
      )
    )

    // #1, it can be just Semigroup not Monoid here
    val phoneBooksMonoid = Monoid[List[Map[String, Int]]]
    val res: Map[String, Int] = phoneBooks.reduce(_ |+| _)
    println(res.toString())

    // #2
    val res2 = combineFold(phoneBooks)
    println(res2.toString())

  }

  // merge shopping carts

  case class ShoppingCart(item: List[String], total: Double)

  implicit val shoppingCartMonoid: Monoid[ShoppingCart] = new Monoid[ShoppingCart] {

    def empty: ShoppingCart = ShoppingCart(List.empty, 0)
    def combine(c1: ShoppingCart, c2: ShoppingCart): ShoppingCart = ShoppingCart(c1.item ++ c2.item, c1.total + c2.total)
    
  }

  def checkin(shoppingCarts: List[ShoppingCart]): ShoppingCart = combineFold(shoppingCarts)


  private def combineFold[T](list: List[T])(implicit monoid: Monoid[T]): T = {
    list.foldLeft(monoid.empty)(_ |+| _)
  }

  // run
  createPhoneBook

  val carts: List[ShoppingCart] = List(
    ShoppingCart(List("item1", "item2"), 20),
    ShoppingCart(List("item3"), 10)
  )

  println(checkin(carts))

}