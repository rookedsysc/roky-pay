package org.roky.pay.membership.application.service

import org.roky.pay.common.UseCase
import org.roky.pay.membership.application.port.`in`.RegisterMembershipCommand
import org.roky.pay.membership.application.port.`in`.RegisterMembershipUseCase
import org.roky.pay.membership.application.port.out.RegisterMembershipPort
import org.roky.pay.membership.domain.Membership
import org.roky.pay.membership.domain.MembershipAddress
import org.roky.pay.membership.domain.MembershipEmail
import org.roky.pay.membership.domain.MembershipIsCorp
import org.roky.pay.membership.domain.MembershipIsValid
import org.roky.pay.membership.domain.MembershipName
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RegisterMembershipService(
    private val registerMembershipPort: RegisterMembershipPort,
) : RegisterMembershipUseCase {
    override fun registerMembership(command: RegisterMembershipCommand): Membership {
        return registerMembershipPort.createMembership(
            name = MembershipName(command.name),
            email = MembershipEmail(command.email),
            address = MembershipAddress(command.address),
            isValid = MembershipIsValid(command.isValid),
            isCorp = MembershipIsCorp(command.isCorp),
        )
    }
}
