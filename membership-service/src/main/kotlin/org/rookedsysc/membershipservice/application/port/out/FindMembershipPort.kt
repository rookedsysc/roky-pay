package org.rookedsysc.membershipservice.application.port.out

import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaEntity
import org.rookedsysc.membershipservice.domain.MemberShip

interface FindMembershipPort {
    fun getMembership(id: Long): MembershipJpaEntity
}
