package org.rookedsysc.membershipservice.adapter.out.persistence

import org.rookedsysc.membershipservice.application.port.out.RegisterMembershipPort
import org.springframework.stereotype.Repository

@Repository
class MembershipPersistenceAdapter(
    private val membershipJpaRepository: MembershipJpaRepository,
) : RegisterMembershipPort {
    override fun createMembership(membershipJpaEntity: MembershipJpaEntity): MembershipJpaEntity {
        return membershipJpaRepository.save(membershipJpaEntity)
    }
}
