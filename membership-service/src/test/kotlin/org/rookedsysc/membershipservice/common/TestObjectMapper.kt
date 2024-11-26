package org.rookedsysc.membershipservice.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class TestObjectMapper {
    companion object {
        fun createObjectMapper(): ObjectMapper {
            return ObjectMapper().registerModule(KotlinModule.Builder().build())
        }
    }
}
