package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import org.rookedsysc.membershipservice.application.port.`in`.usecase.FindMembershipUsecase
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class FindMembershipController(
    private val findMembershipUsecase: FindMembershipUsecase
) {

    @GetMapping("/membership/{membershipId}")
    fun getMembership(@PathVariable membershipId: Long): ResponseEntity<MemberShip> {
        val response: MemberShip = findMembershipUsecase.getMembership(membershipId)
        return ResponseEntity.ok(response)
    }
}
