//> using scala 3.3.0
//> using publish.organization org.virtuslab
//> using	publish.name compat-test-util-lib
//> using	publish.version 0.2.0
//> using dep org.virtuslab::compat-test-base-lib:0.2.0

def bar() = foo(z = "a", x = 1)
def baz() = foo(z = "b", x = 2, y = true)