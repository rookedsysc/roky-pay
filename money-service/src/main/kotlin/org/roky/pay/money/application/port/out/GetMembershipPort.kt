package org.roky.pay.money.application.port.out

interface GetMembershipPort {
    fun getMembershipStatus(membershipId: String): Boolean
}
