package org.rookedsysc.membershipservice.application.port.`in`.usecase

import org.rookedsysc.membershipservice.domain.MemberShip

interface FindMembershipUsecase {
    fun getMembership(id: Long): MemberShip
}
