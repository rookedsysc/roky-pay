package org.rookedsysc.membershipservice.application.port.`in`.dto

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.membershipservice.common.ValidationUtils

data class RegisterMembershipCommand(
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
) {
    init {
        ValidationUtils.validate(this)
    }
}
