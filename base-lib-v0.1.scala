//> using scala 3.3.0
//> using publish.organization org.virtuslab
//> using	publish.name compat-test-base-lib
//> using	publish.version 0.1.0

trait FakeProduct:
  def productElems: List[Any]

final class Foo private () extends FakeProduct derives Encoder:
  private var x: Int = compiletime.uninitialized
  private var z: String = compiletime.uninitialized

  def productElems: List[Any] = List(x, z)

  override def toString(): String = s"Foo(x = $x, z = $z)"

trait Encoder[A]:
  def encode(a: A): String

object Encoder:
  import scala.deriving.*
  import scala.compiletime.summonAll

  inline def derived[A <: FakeProduct](using
      m: Mirror.ProductOf[A]
  ): Encoder[A] =
    val elemInstances = summonAll[Tuple.Map[m.MirroredElemTypes, Encoder]]
    val elemLabels = summonAll[Tuple.Map[m.MirroredElemLabels, ValueOf]]
    new Encoder[A]:
      def encode(a: A): String =
        a.productElems
          .zip(elemInstances.toList)
          .map { case (elem, inst) =>
            inst.asInstanceOf[Encoder[Any]].encode(elem)
          }
          .zip(elemLabels.toList.map(_.asInstanceOf[ValueOf[?]].value))
          .map { case (elem, label) =>
            s"\"$label\":\"$elem\""
          }
          .mkString("{", ",", "}")

  given Encoder[Int] = _.toString
  given Encoder[String] = a => a
  given Encoder[Boolean] = _.toString

object Foo:
  import scala.deriving.Mirror

  private def constructor(): Foo = new Foo()

  inline def apply(x: Int, z: String): Foo =
    val f = constructor()
    f.x = x
    f.z = z
    f

  given Mirror.Product with
    type MirroredType = Foo
    type MirroredMonoType = Foo
    type MirroredElemTypes = (Int, String)
    override type MirroredElemLabels = ("x", "z")

    def fromProduct(p: scala.Product): Foo = p match {
      case (x: Int, z: String) => Foo(x, z)
    }
