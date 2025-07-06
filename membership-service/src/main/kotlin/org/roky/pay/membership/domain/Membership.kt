package org.roky.pay.membership.domain

import org.roky.pay.membership.domain.MembershipId
import org.roky.pay.membership.domain.MembershipName
import org.roky.pay.membership.domain.MembershipEmail
import org.roky.pay.membership.domain.MembershipAddress
import org.roky.pay.membership.domain.MembershipIsValid
import org.roky.pay.membership.domain.MembershipIsCorp

data class Membership(
    val membershipId: MembershipId,
    val name: MembershipName,
    val email: MembershipEmail,
    val address: MembershipAddress,
    val isValid: MembershipIsValid,
    val isCorp: MembershipIsCorp,
) {
    companion object {
        fun create(
            membershipId: MembershipId,
            name: MembershipName,
            email: MembershipEmail,
            address: MembershipAddress,
            isValid: MembershipIsValid,
            isCorp: MembershipIsCorp,
        ) = Membership(
            membershipId = membershipId,
            name = name,
            email = email,
            address = address,
            isValid = isValid,
            isCorp = isCorp,
        )
    }
}
