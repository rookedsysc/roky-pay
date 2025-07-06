package org.roky.pay.membership.application.port.`in`

import org.roky.pay.membership.domain.Membership

interface FindMembershipUseCase {
    fun findMembership(command: FindMembershipCommand): Membership
}
