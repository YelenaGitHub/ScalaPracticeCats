import cats.effect.{ExitCode, IO, IOApp}

import cats.instances.list._
import cats.syntax.parallel._

import scala.util.Random

object IOs extends IOApp {

/*
есть лист целых рандомных чисел меньших 10
val list = List(2,0, 4, -2)

нужно сделать функцию которая
возведет  2 в степень каждого элемента 2^x параллельно
и посчитает сумму этих чисел
*/

// import scala.concurrent.ExecutionContext

  override def run(args: List[String]): IO[ExitCode] = {

    val list = (1 to 10).map(_ => Random.nextInt() % 10).toList
    println(list)

    val res: IO[List[Double]] = list.parTraverse { i => IO {
      Math.pow(2, i)
    }}

    res.map(_ => ExitCode(0))
    // unsafeRunSync()
  }

}
