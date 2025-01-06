package org.rookedsysc.membershipservice.application.port.`in`.usecase

import org.rookedsysc.membershipservice.application.port.`in`.dto.ModifyMembershipCommand
import org.rookedsysc.membershipservice.domain.MemberShip

interface ModifyMembershipUsecase {
    fun modifyMembership(modifyMembershipCommand: ModifyMembershipCommand): MemberShip
}
