package org.roky.pay.membership.application.port.`in`

import org.roky.pay.membership.domain.Membership

interface ModifyMembershipUseCase {
    fun modifyMembership(command: ModifyMembershipCommand): Membership
}
