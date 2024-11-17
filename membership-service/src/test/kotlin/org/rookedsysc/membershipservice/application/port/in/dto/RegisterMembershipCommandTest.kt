package org.rookedsysc.membershipservice.application.port.`in`.dto

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class RegisterMembershipCommandTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    @DisplayName("유효한 RegisterMembershipCommand 테스트")
    fun `test valid RegisterMembershipCommand`() {
        // given
        val command = RegisterMembershipCommand(
            name = "John Doe",
            email = "john.doe@example.com",
            address = "123 Main St",
            isValid = true,
            isCorp = true
        )

        // when
        val violations = validator.validate(command)

        // then
        assertEquals(0, violations.size)
    }

    @Test
    @DisplayName("유효하지 않은 RegisterMembershipCommand 생성 테스트")
    fun testInvalidRegisterMembershipCommandCreation() {
        // given // when // then
        assertThrows(ConstraintViolationException::class.java) {
            RegisterMembershipCommand(
                name = "",
                email = "invalid-email",
                address = "",
                isValid = false,
                isCorp = true
            )
        }.also { exception ->
            // violation 개수 검증
            assertEquals(4, exception.constraintViolations.size)

            // 각각의 위반 사항 검증
            val violations = exception.constraintViolations.map { it.propertyPath.toString() }
            assertAll(
                { assertTrue(violations.contains("name")) },
                { assertTrue(violations.contains("email")) },
                { assertTrue(violations.contains("address")) },
                { assertTrue(violations.contains("isValid")) }
            )
        }
    }
}
