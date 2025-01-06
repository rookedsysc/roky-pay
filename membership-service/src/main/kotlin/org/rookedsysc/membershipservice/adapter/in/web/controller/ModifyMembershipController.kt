package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.rookedsysc.membershipservice.adapter.`in`.web.dto.ModifyMembershipRequest
import org.rookedsysc.membershipservice.aop.MembershipTag
import org.rookedsysc.membershipservice.application.port.`in`.dto.ModifyMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.usecase.ModifyMembershipUsecase
import org.rookedsysc.membershipservice.common.constant.MembershipConstants
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@MembershipTag
@RequestMapping(MembershipConstants.MEMBERSHIP)
class ModifyMembershipController(
    private val modifyMembershipUsecase: ModifyMembershipUsecase
) {

    @Operation(
        summary = "회원 정보 수정",
        description = "주어진 회원 ID를 기반으로 회원 정보를 수정합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공적으로 수정됨",
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "회원 정보를 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 오류",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PutMapping("/{id}")
    fun modifyMembership(
        @RequestBody modifyMembershipRequest: ModifyMembershipRequest,
        @PathVariable id: Long
    ): Unit {
        val modifyMembershipCommand: ModifyMembershipCommand = ModifyMembershipCommand(
            id = id,
            name = modifyMembershipRequest.name,
            address = modifyMembershipRequest.address,
            email = modifyMembershipRequest.email,
            isCorp = modifyMembershipRequest.isCorp
        )

        modifyMembershipUsecase.modifyMembership(modifyMembershipCommand)
    }
}

