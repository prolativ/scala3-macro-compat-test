//> using scala 3.3.0
//> using publish.organization org.virtuslab
//> using	publish.name compat-test-base-lib
//> using	publish.version 0.1.0

inline def foo(x: Int, z: String): Map[String, String] =
  Map(
    "x" -> x.toString,
    "z" -> z.toString
  )