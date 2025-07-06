package org.roky.pay.membership.application.service

import org.roky.pay.common.UseCase
import org.roky.pay.membership.application.port.`in`.ModifyMembershipCommand
import org.roky.pay.membership.application.port.`in`.ModifyMembershipUseCase
import org.roky.pay.membership.application.port.out.ModifyMembershipPort
import org.roky.pay.membership.domain.Membership
import org.roky.pay.membership.domain.MembershipId
import org.roky.pay.membership.domain.MembershipName
import org.roky.pay.membership.domain.MembershipEmail
import org.roky.pay.membership.domain.MembershipAddress
import org.roky.pay.membership.domain.MembershipIsValid
import org.roky.pay.membership.domain.MembershipIsCorp
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class ModifyMembershipService(
    private val modifyMembershipPort: ModifyMembershipPort,
) : ModifyMembershipUseCase {
    override fun modifyMembership(command: ModifyMembershipCommand): Membership {
        return modifyMembershipPort.modifyMembership(
            membershipId = MembershipId(command.membershipId),
            name = MembershipName(command.name),
            email = MembershipEmail(command.email),
            address = MembershipAddress(command.address),
            isValid = MembershipIsValid(command.isValid),
            isCorp = MembershipIsCorp(command.isCorp),
        )
    }
}