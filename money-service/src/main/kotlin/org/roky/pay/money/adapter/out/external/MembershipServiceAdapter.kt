package org.roky.pay.money.adapter.out.external

import org.roky.pay.common.ServiceAdapter
import org.roky.pay.money.application.port.out.GetMembershipPort

@ServiceAdapter
class MembershipServiceAdapter : GetMembershipPort {
    override fun getMembershipStatus(membershipId: String): Boolean {
        // TODO: Implement actual membership service call
        return true
    }
}
