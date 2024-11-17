package org.rookedsysc.membershipservice.adapter.out.persistence

import org.rookedsysc.membershipservice.application.port.out.FindMembershipPort
import org.rookedsysc.membershipservice.application.port.out.RegisterMembershipPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MembershipPersistenceAdapter(
    private val membershipJpaRepository: MembershipJpaRepository,
) : RegisterMembershipPort, FindMembershipPort {
    override fun createMembership(membershipJpaEntity: MembershipJpaEntity): MembershipJpaEntity {
        return membershipJpaRepository.save(membershipJpaEntity)
    }

    override fun getMembership(id: Long): MembershipJpaEntity {
        return membershipJpaRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Membership not found")
    }
}
