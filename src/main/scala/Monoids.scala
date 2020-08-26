package monoid

import cats.Monoid
import cats.instances.int._
import cats.instances.list._
import cats.instances.map._
import cats.syntax.monoid._

object Monoids extends App {

  // combine a list of phonebooks as Maps[String, Int]

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
  def combineFold[T](list: List[T])(implicit monoid: Monoid[T]): T = {
    list.foldLeft(monoid.empty)(_ |+| _)
  }

  val res2 = combineFold(phoneBooks)
  println(res2.toString())

}