package org.roky.pay.membership.application.port.out

import org.roky.pay.membership.domain.Membership
import org.roky.pay.membership.domain.MembershipId

interface FindMembershipPort {
    fun findMembership(membershipId: MembershipId): Membership?
}
