package org.rookedsysc.membershipservice.application.service

import io.mockk.every
import io.mockk.mockk
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipMapper
import org.rookedsysc.membershipservice.adapter.out.persistence.MockMembershipJpaEntity
import org.rookedsysc.membershipservice.application.port.`in`.dto.MockRegisterMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.dto.RegisterMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.usecase.RegisterMembershipUsecase
import org.rookedsysc.membershipservice.application.port.out.RegisterMembershipPort

@ExtendWith(MockitoExtension::class)
class RegisterMembershipServiceTest {

    private val registerMembershipPort: RegisterMembershipPort = mockk()
    private val membershipMapper = MembershipMapper()
    private val registerMembershipUsecase: RegisterMembershipUsecase =
        RegisterMembershipService(registerMembershipPort, membershipMapper)

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

    @Test
    @DisplayName("정상적인 Command가 입력될 경우 Membership 생성 성공")
    fun `registerMembership should success if valid command is given`() {
        // given
        val name = "홍길동"
        val command = MockRegisterMembershipCommand.createRegisterMembershipCommandMock(name)
        val jpaEntity = MockMembershipJpaEntity.createMembershipJpaEntityMock(name)
        val expect = membershipMapper.toDomainEntity(jpaEntity)
        every { registerMembershipPort.createMembership(any()) } returns jpaEntity

        // when
        val result = registerMembershipUsecase.registerMembership(command)

        // then
        assertThat(result.name).isEqualTo(expect.name)
        assertThat(result.email).isEqualTo(expect.email)
        assertThat(result.address).isEqualTo(expect.address)
        assertThat(result.id).isEqualTo(expect.id)
        assertThat(result.isValid).isEqualTo(expect.isValid)
        assertThat(result.isCorp).isEqualTo(expect.isCorp)
    }
}
