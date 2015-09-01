package com.tesla005.kafka

import java.util.Properties
import java.io.File
import backtype.storm.generated.KillOptions
import backtype.storm.topology.TopologyBuilder
import backtype.storm.{Config, LocalCluster}
import com.tesla005.logging.LazyLogging
import kafka.admin.AdminUtils
import kafka.utils.ZKStringSerializer
import org.I0Itec.zkclient.ZkClient
import storm.kafka.{KafkaSpout, SpoutConfig, ZkHosts}
import kafka.server.{KafkaConfig, KafkaServerStartable}
import org.apache.commons.io.FileUtils
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.test.TestingServer
import scala.concurrent.duration._

object kafkastart extends LazyLogging
{
  def main (args: Array[String]) {

    val port = 2181
    logger.info("port set at " + port)
    val defaultZkConnect = "127.0.0.1:2181"
    val server = new TestingServer(port)

    val logDir = {
      val random = (new scala.util.Random).nextInt()
      val path = Seq(System.getProperty("java.io.tmpdir"), "kafka_dir", "log" + random).mkString(File.separator)
      new File(path)
    }

    val config: Properties = new Properties

    val finalconfig = {
      val c = new Properties
      c.load(this.getClass().getResourceAsStream("/test.conf"))
      c.putAll(config)
      c.setProperty("log.dirs", logDir.getAbsolutePath)
      c
    }

    val kafkaConfig = new KafkaConfig(finalconfig)
    val kafka = new KafkaServerStartable(kafkaConfig)
    val brokerlist = kafka.serverConfig.hostName + ":" + kafka.serverConfig.port

    val zookeeperConnect = {
      val zkConnectLookup = Option(finalconfig.getProperty("zookeeper.connect"))
      zkConnectLookup match {
        case Some(zkConnect) => zkConnect
        case _ =>
          defaultZkConnect
          logger.debug(s"zk not configured, reverting to original settings $defaultZkConnect")
      }
    }


    logger.debug(s"(zookeeper server at $zookeeperConnect) ...")
    logger.debug(s"starting kafka server at $brokerlist...")
    kafka.startup()

    def start() {

    }

    def stop() {
      logger.debug(s"Shutting down embedded Kafka broker at $brokerlist (with ZK server at $zookeeperConnect)...")
      kafka.shutdown()
      FileUtils.deleteQuietly(logDir)
      logger.debug(s"Shutdown of embedded Kafka broker at $brokerlist completed (with ZK server at $zookeeperConnect)")
    }


}
}