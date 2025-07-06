package org.roky.pay.membership.adapter.`in`.web

import org.roky.pay.common.WebAdapter
import org.roky.pay.membership.application.port.`in`.ModifyMembershipCommand
import org.roky.pay.membership.application.port.`in`.ModifyMembershipUseCase
import org.roky.pay.membership.domain.Membership
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class ModifyMembershipController(
    private val modifyMembershipUseCase: ModifyMembershipUseCase,
) {

    @PostMapping("/membership/modify")
    fun modifyMembershipByMemberId(@RequestBody request: ModifyMembershipRequest): ResponseEntity<Membership> {
        val command = ModifyMembershipCommand(
            membershipId = request.membershipId,
            name = request.name,
            address = request.address,
            email = request.email,
            isValid = request.isValid,
            isCorp = request.isCorp,
        )
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command))
    }
}
