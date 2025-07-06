package org.roky.pay.membership.adapter.`in`.web

import org.roky.pay.common.WebAdapter
import org.roky.pay.membership.application.port.`in`.FindMembershipCommand
import org.roky.pay.membership.application.port.`in`.FindMembershipUseCase
import org.roky.pay.membership.domain.Membership
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindMembershipController(
    private val findMembershipUseCase: FindMembershipUseCase,
) {

    @GetMapping("/membership/{membershipId}")
    fun findMembershipByMemberId(@PathVariable membershipId: String): ResponseEntity<Membership> {
        val command = FindMembershipCommand(membershipId)
        return ResponseEntity.ok(findMembershipUseCase.findMembership(command))
    }
}
