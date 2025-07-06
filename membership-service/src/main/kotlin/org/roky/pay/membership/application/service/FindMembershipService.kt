package org.roky.pay.membership.application.service

import org.roky.pay.common.UseCase
import org.roky.pay.membership.application.port.`in`.FindMembershipCommand
import org.roky.pay.membership.application.port.`in`.FindMembershipUseCase
import org.roky.pay.membership.application.port.out.FindMembershipPort
import org.roky.pay.membership.domain.MembershipId

import org.roky.pay.membership.domain.Membership
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional(readOnly = true)
class FindMembershipService(
    private val findMembershipPort: FindMembershipPort,
) : FindMembershipUseCase {
    override fun findMembership(command: FindMembershipCommand): Membership {
        return findMembershipPort.findMembership(
            membershipId = MembershipId(command.membershipId)
        ) ?: throw NoSuchElementException("Membership not found")
    }
}
