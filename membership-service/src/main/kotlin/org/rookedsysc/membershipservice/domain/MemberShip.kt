package org.rookedsysc.membershipservice.domain

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.membershipservice.application.port.`in`.dto.ModifyMembershipCommand
import org.rookedsysc.membershipservice.common.ValidationUtils

//TODO: Membership으로 변경
class MemberShip(
    @field:NotBlank
    val id: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val address: String,

    @field:AssertTrue
    val isValid: Boolean,

    @field:NotNull
    val isCorp: Boolean
) {}
