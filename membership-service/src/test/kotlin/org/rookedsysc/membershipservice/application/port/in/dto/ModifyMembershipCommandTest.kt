package org.rookedsysc.membershipservice.application.port.`in`.dto

import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ModifyMembershipCommandTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    @DisplayName("ID만 Null이 아니면 ModifyMembershipCommand 생성 테스트")
    fun `id is valid - should pass validation`() {
        val command = ModifyMembershipCommand(
            id = 1L,
            name = null,
            address = null,
            email = null,
            isCorp = null
        )

        val violations = validator.validate(command)
        assertEquals(0, violations.size, "Validation should pass when id is valid")
    }
}
