package com.tesla005.logging

import org.slf4j.{Logger, LoggerFactory}

trait LazyLogging {

  protected lazy val logger: Logger = LoggerFactory.getLogger(getClass.getName)

}

trait StrictLogging {

  protected val logger: Logger = LoggerFactory.getLogger(getClass.getName)

}