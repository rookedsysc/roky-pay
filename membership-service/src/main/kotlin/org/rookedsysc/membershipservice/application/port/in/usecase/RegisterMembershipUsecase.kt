package org.rookedsysc.membershipservice.application.port.`in`.usecase

import org.rookedsysc.membershipservice.application.port.`in`.dto.RegisterMembershipCommand
import org.rookedsysc.membershipservice.domain.MemberShip

interface RegisterMembershipUsecase {
    fun registerMembership(command: RegisterMembershipCommand): MemberShip
}
