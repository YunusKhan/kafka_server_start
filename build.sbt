import _root_.sbt.Keys._
import _root_.sbt._


val bijectionVersion = "0.7.1"
val chillVersion = "0.5.1"
val sparkVersion = "1.1.1"
val stormVersion = "0.9.3"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "org.specs2" % "specs2_2.10" % "3.3.1",
  "net.databinder" %% "unfiltered-directives" % "0.7.1",
  "net.databinder" %% "unfiltered-filter" % "0.7.1",
  "net.databinder" %% "unfiltered-jetty" % "0.7.1",
  "net.databinder" %% "unfiltered-spec" % "0.7.1" % "test",
  "net.databinder" %% "unfiltered-specs2" % "0.7.1" % "test",
  "com.novocode" % "junit-interface" % "0.8" % "test->default",
  "com.101tec" % "zkclient" % "0.4",
  "org.apache.curator" % "curator-test" % "2.8.0",
  "org.apache.kafka" % "kafka_2.10" % "0.8.2.1",
  "commons-io" % "commons-io" % "2.4",
  "org.apache.curator" % "curator-framework" % "2.8.0",
  "org.apache.curator" % "curator-recipes" % "2.8.0",
  "com.twitter" %% "bijection-core" % bijectionVersion,
  "com.twitter" %% "bijection-avro" % bijectionVersion,
  "com.twitter" %% "chill" % chillVersion,
  "com.twitter" %% "chill-avro" % chillVersion,
  "com.twitter" %% "chill-bijection" % chillVersion,
  // The excludes of jms, jmxtools and jmxri are required as per https://issues.apache.org/jira/browse/KAFKA-974.
  // The exclude of slf4j-simple is because it overlaps with our use of logback with slf4j facade;  without the exclude
  // we get slf4j warnings and logback's configuration is not picked up.
  "org.apache.kafka" % "kafka_2.10" % "0.8.2.1"
    exclude("javax.jms", "jms")
    exclude("com.sun.jdmk", "jmxtools")
    exclude("com.sun.jmx", "jmxri")
    exclude("org.slf4j", "slf4j-simple")
    exclude("log4j", "log4j")
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("com.101tec", "zkclient"),
  "org.apache.storm" % "storm-core" % stormVersion % "provided"
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("org.slf4j", "log4j-over-slf4j"),
  "org.apache.storm" % "storm-kafka" % stormVersion
    exclude("org.apache.zookeeper", "zookeeper"),
  "org.apache.spark" %% "spark-core" % sparkVersion
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("org.slf4j", "slf4j-api")
    exclude("org.slf4j", "slf4j-log4j12")
    exclude("org.slf4j", "jul-to-slf4j")
    exclude("org.slf4j", "jcl-over-slf4j")
    exclude("com.twitter", "chill_2.10")
    exclude("log4j", "log4j"),
  "org.apache.spark" %% "spark-streaming-kafka" % sparkVersion
    exclude("org.apache.zookeeper", "zookeeper"),
  "com.101tec" % "zkclient" % "0.4"
    exclude("org.apache.zookeeper", "zookeeper"),
  "org.apache.curator" % "curator-test" % "2.8.0"
    exclude("org.jboss.netty", "netty")
    exclude("org.slf4j", "slf4j-log4j12"),
  "commons-io" % "commons-io" % "2.4",
  "org.apache.commons" % "commons-pool2" % "2.3",
  // Logback with slf4j facade
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  // Test dependencies
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test",
  "org.apache.storm" % "storm-core" % "0.10.0-beta1"
)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "clj-time" at "http://clojars.org/repo/"

scalaVersion := "2.10.4"

mainClass in (Compile,run) := Some("com.tesla005.kafka.kafkastart")



