package org.roky.pay.membership.application.port.`in`

import org.roky.pay.membership.domain.Membership

interface RegisterMembershipUseCase {
    fun registerMembership(command: RegisterMembershipCommand): Membership
}
