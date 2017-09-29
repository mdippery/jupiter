# Jupiter

## Build

Jupiter can be built using [sbt]:

    $ sbt compile

You can run the test suite using [sbt], as well:

    $ sbt test

## Install

You can depend on it in your own projects by adding Jupiter as a library
dependency in `build.sbt`:

    resolvers += Resolver.bintrayRepo("mipadi", "maven"),

    libraryDependencies ++= Seq(
      "com.mipadi" %% "jupiter" % "1.0.0"
    )

## Documentation

The latest Jupiter API documentation is available
[here](https://mdippery.github.io/jupiter/).

  [sbt]: http://www.scala-sbt.org/
