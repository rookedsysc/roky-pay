package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import org.rookedsysc.membershipservice.adapter.`in`.web.dto.RegisterMembershipRequest
import org.rookedsysc.membershipservice.application.port.`in`.dto.RegisterMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.usecase.RegisterMembershipUsecase
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterMembershipController(
    val registerMembershipUsecase: RegisterMembershipUsecase
) {
    @PostMapping("/membership/register")
    fun registerMembership(
        @RequestBody registerMembershipRequest: RegisterMembershipRequest
    ): MemberShip {
        val command = RegisterMembershipCommand(
            name = registerMembershipRequest.name,
            email = registerMembershipRequest.email,
            address = registerMembershipRequest.address,
            isValid = registerMembershipRequest.isValid,
            isCorp = registerMembershipRequest.isCorp
        )

        return registerMembershipUsecase.registerMembership(command)
    }
}
