package org.rookedsysc.membershipservice.application.port.out

import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaEntity

interface ModifyMembershipPort {
    fun modifyMembership(membershipJpaEntity: MembershipJpaEntity): MembershipJpaEntity
}
