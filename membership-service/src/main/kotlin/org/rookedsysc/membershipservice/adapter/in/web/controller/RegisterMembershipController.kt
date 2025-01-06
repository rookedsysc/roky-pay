package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.rookedsysc.membershipservice.adapter.`in`.web.dto.RegisterMembershipRequest
import org.rookedsysc.membershipservice.aop.MembershipTag
import org.rookedsysc.membershipservice.application.port.`in`.dto.RegisterMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.usecase.RegisterMembershipUsecase
import org.rookedsysc.membershipservice.common.constant.MembershipConstants
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@MembershipTag
@RequestMapping(MembershipConstants.MEMBERSHIP)
class RegisterMembershipController(
    val registerMembershipUsecase: RegisterMembershipUsecase
) {

    @Operation(summary = "새로운 멤버쉽 등록")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "성공적으로 등록됨",
                content = [Content(schema = Schema(implementation = MemberShip::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 오류",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PostMapping("/register")
    fun registerMembership(
        @Valid @RequestBody registerMembershipRequest: RegisterMembershipRequest
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
