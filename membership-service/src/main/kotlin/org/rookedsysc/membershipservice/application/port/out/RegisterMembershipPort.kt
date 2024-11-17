package org.rookedsysc.membershipservice.application.port.out

import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaEntity
import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaRepository

interface RegisterMembershipPort {
    fun createMembership(membershipJpaEntity: MembershipJpaEntity): MembershipJpaEntity
}
