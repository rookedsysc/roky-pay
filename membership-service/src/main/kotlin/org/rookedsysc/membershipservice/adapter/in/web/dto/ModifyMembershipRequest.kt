package org.rookedsysc.membershipservice.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.membershipservice.aop.NullableEmail

@Schema(description = "회원 수정 요청")
data class ModifyMembershipRequest(
    @field:Schema(description = "회원 ID", example = "1")
    val id: Long,

    @field:Schema(description = "회원 이름", example = "John Doe", required = false)
    val name: String?,

    @field:Schema(description = "회원 주소", example = "123 Main St", required = false)
    val address: String?,

    @field:NullableEmail
    @field:Schema(description = "회원 이메일", example = "john.doe@example.com", required = false)
    val email: String?,

    @field:Schema(description = "법인 여부", example = "false", required = false)
    val isCorp: Boolean?
) {}
