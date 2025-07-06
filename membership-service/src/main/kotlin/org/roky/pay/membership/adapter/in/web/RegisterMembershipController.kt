package org.roky.pay.membership.adapter.`in`.web

import org.roky.pay.common.WebAdapter
import org.roky.pay.membership.application.port.`in`.RegisterMembershipCommand
import org.roky.pay.membership.application.port.`in`.RegisterMembershipUseCase
import org.roky.pay.membership.domain.Membership
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterMembershipController(
    private val registerMembershipUseCase: RegisterMembershipUseCase,
) {

    @PostMapping("/membership/register")
    fun registerMembership(@RequestBody request: RegisterMembershipRequest): Membership {
        val command = RegisterMembershipCommand(
            name = request.name,
            address = request.address,
            email = request.email,
            isValid = true,
            isCorp = request.isCorp,
        )
        return registerMembershipUseCase.registerMembership(command)
    }
}
