package org.rookedsysc.membershipservice.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface LogConfig {
    val log: Logger get() = LoggerFactory.getLogger(this.javaClass)
}
