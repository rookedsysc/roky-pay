package org.rookedsysc.membershipservice.adapter.`in`.web.dto

import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ModifyMembershipRequestTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `email is null - should pass validation`() {
        val request = ModifyMembershipRequest(
            id = 1L,
            name = "John Doe",
            address = "123 Main St",
            email = null,
            isCorp = false
        )

        val violations = validator.validate(request)
        assertEquals(0, violations.size, "Validation should pass when email is null")
    }

    @Test
    fun `email is valid - should pass validation`() {
        val request = ModifyMembershipRequest(
            id = 1L,
            name = "John Doe",
            address = "123 Main St",
            email = "john.doe@example.com",
            isCorp = false
        )

        val violations = validator.validate(request)
        assertEquals(0, violations.size, "Validation should pass when email is valid")
    }

    @Test
    fun `email is invalid - should fail validation`() {
        val request = ModifyMembershipRequest(
            id = 1L,
            name = "John Doe",
            address = "123 Main St",
            email = "invalid-email",
            isCorp = false
        )

        val violations = validator.validate(request)
        assertEquals(1, violations.size, "Validation should fail when email is invalid")
        assertEquals("Invalid email format", violations.first().message)
    }
}
