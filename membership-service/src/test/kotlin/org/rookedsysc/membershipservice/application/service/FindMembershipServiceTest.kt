package org.rookedsysc.membershipservice.application.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipMapper
import org.rookedsysc.membershipservice.adapter.out.persistence.MockMembershipJpaEntity
import org.rookedsysc.membershipservice.application.port.out.FindMembershipPort
import org.rookedsysc.membershipservice.domain.MockMemberShip
import kotlin.test.Test

class FindMembershipServiceTest {

    private val findMembershipPort: FindMembershipPort = mockk()
    private val membershipMapper: MembershipMapper = mockk()
    private val findMembershipService = FindMembershipService(findMembershipPort, membershipMapper)

    @Test
    @DisplayName("Membership이 존재할 경우 반환")
    fun `getMembership should return membership when id is valid`() {
        // given
        val id = 1L
        val name = "홍길동"
        val membershipJpaEntity = MockMembershipJpaEntity.createMembershipJpaEntityMock(name)
        val membership = MockMemberShip.createMemberShipMock(name)

        every { findMembershipPort.getMembership(id) } returns membershipJpaEntity
        every { membershipMapper.toDomainEntity(membershipJpaEntity) } returns membership

        // when
        val result = findMembershipService.getMembership(id)

        // then
        verify { findMembershipPort.getMembership(id) }
        verify { membershipMapper.toDomainEntity(membershipJpaEntity) }
        assertThat(result).isEqualTo(membership)
    }

    @Test
    @DisplayName("Membership이 없을 경우 예외 발생")
    fun `getMembership should throw exception when membership not found`() {
        // given
        val id = 1L
        every { findMembershipPort.getMembership(id) } throws IllegalArgumentException("Membership not found")

        // when
        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            findMembershipService.getMembership(id)
        }

        // then
        assertThat(exception.message).isEqualTo("Membership not found")
        verify { findMembershipPort.getMembership(id) }
        verify(exactly = 0) { membershipMapper.toDomainEntity(any()) }
    }
}
