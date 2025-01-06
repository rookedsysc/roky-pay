package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.rookedsysc.membershipservice.aop.MembershipTag
import org.rookedsysc.membershipservice.application.port.`in`.usecase.FindMembershipUsecase
import org.rookedsysc.membershipservice.common.constant.MembershipConstants
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@MembershipTag
@RequestMapping(MembershipConstants.MEMBERSHIP)
class FindMembershipController(
    private val findMembershipUsecase: FindMembershipUsecase
) {

    @Operation(summary = "멤버쉽 조회", description = "멤버쉽 ID로 멤버쉽 정보를 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "성공적으로 조회됨",
                content = [Content(schema = Schema(implementation = MemberShip::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "멤버쉽을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 오류",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @GetMapping("/{membershipId}")
    fun getMembership(@PathVariable membershipId: Long): ResponseEntity<MemberShip> {
        val response: MemberShip = findMembershipUsecase.getMembership(membershipId)
        return ResponseEntity.ok(response)
    }
}
