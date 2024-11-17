package org.rookedsysc.membershipservice.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "회원 등록 요청")
data class RegisterMembershipRequest(
    @field:NotBlank
    @field:Schema(description = "회원 이름", example = "John Doe")
    val name: String,

    @field:NotBlank
    @field:Schema(description = "회원 주소", example = "123 Main St")
    val address: String,

    @field:NotBlank
    @field:Email
    @field:Schema(description = "회원 이메일", example = "john.doe@example.com")
    val email: String,

    @field:AssertTrue
    @field:Schema(description = "유효성 여부", example = "true")
    val isValid: Boolean,

    @field:NotNull
    @field:Schema(description = "법인 여부", example = "false")
    val isCorp: Boolean
)
