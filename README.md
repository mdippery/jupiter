# Jupiter

## Build

Jupiter can be built using [sbt]:

    $ sbt compile

You can run the test suite using [sbt], as well:

    $ sbt test

## Install

If you want to use Jupiter in your own projects, it's probably easiest to
publish Jupiter to your local Ivy repository:

    $ sbt publishLocal

You can then depend on it in your own projects by adding Jupiter as a library
dependency in `build.sbt`:

    libraryDependencies ++= Seq(
      "com.mipadi" %% "jupiter" % "1.0.0-SNAPSHOT"
    )

## Documentation

The latest Jupiter API documentation is available
[here](https://mdippery.github.io/jupiter/).

  [sbt]: http://www.scala-sbt.org/
