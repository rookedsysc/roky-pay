package org.roky.pay.membership.application.port.out

import org.roky.pay.membership.domain.Membership
import org.roky.pay.membership.domain.MembershipId
import org.roky.pay.membership.domain.MembershipName
import org.roky.pay.membership.domain.MembershipEmail
import org.roky.pay.membership.domain.MembershipAddress
import org.roky.pay.membership.domain.MembershipIsValid
import org.roky.pay.membership.domain.MembershipIsCorp

interface RegisterMembershipPort {
    fun createMembership(
        name: MembershipName,
        email: MembershipEmail,
        address: MembershipAddress,
        isValid: MembershipIsValid,
        isCorp: MembershipIsCorp,
    ): Membership
}
