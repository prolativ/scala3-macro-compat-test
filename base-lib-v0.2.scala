//> using scala 3.3.0
//> using publish.organization org.virtuslab
//> using	publish.name compat-test-base-lib
//> using	publish.version 0.2.0

inline def foo(x: Int, y: Boolean = false, z: String): Map[String, String] =
  Map(
    "x" -> x.toString,
    "y" -> y.toString,
    "z" -> z.toString
  )