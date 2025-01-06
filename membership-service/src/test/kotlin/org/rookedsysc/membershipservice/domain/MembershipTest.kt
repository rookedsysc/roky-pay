package org.rookedsysc.membershipservice.domain

import jakarta.validation.ConstraintViolationException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MembershipTest {
    @Test
    @DisplayName("유효하지 않은 Membership 생성시 Exception 발생")
    fun testInvalidMembershipCreation() {
        // given // when // then
        assertThrows(ConstraintViolationException::class.java) {
            MemberShip(
                id = "",
                name = "",
                email = "invalid-email",
                address = "",
                isValid = false,
                isCorp = true
            )
        }.also { exception ->
            // violation 개수 검증
            assertEquals(5, exception.constraintViolations.size)

            // 각각의 위반 사항 검증
            val violations = exception.constraintViolations.map { it.propertyPath.toString() }
            assertAll(
                { assertTrue(violations.contains("name")) },
                { assertTrue(violations.contains("id")) },
                { assertTrue(violations.contains("email")) },
                { assertTrue(violations.contains("address")) },
                { assertTrue(violations.contains("isValid")) }
            )
        }
    }
}
