//> using scala 3.3.0
//> using dep org.virtuslab::compat-test-base-lib:0.1.0
//> using dep org.virtuslab::compat-test-util-lib:0.1.0

@main def run() =
  println("base-lib version: 0.1.0, util-lib version: 0.1.0")
  println(foo(z = "aaa", x = 10))
  println(bar())
  // println(foo(z = "aaa", x = 10, y = true))
  // println(baz())