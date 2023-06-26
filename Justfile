publish-local:
  scala-cli clean .
  scala-cli publish local base-lib-v0.1.scala --doc=false
  scala-cli publish local base-lib-v0.2.scala --doc=false
  scala-cli publish local util-lib-v0.1.scala --doc=false
  scala-cli publish local util-lib-v0.2.scala --doc=false

run:
  scala-cli run usage-base1-util1.scala
  scala-cli run usage-base1-util2.scala
  scala-cli run usage-base2-util1.scala
  scala-cli run usage-base2-util2.scala