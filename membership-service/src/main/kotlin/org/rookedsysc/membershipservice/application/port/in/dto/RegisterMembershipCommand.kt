package org.rookedsysc.membershipservice.application.port.`in`.dto

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.membershipservice.common.LogConfig

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
    companion object : LogConfig
    init {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        val validator: Validator = factory.validator
        val violations = validator.validate(this)
        if (violations.isNotEmpty()) {
            for(violation in violations) {
                log.error("RegisterMembershipCommand validation error: ${violation.message}")
            }
            throw ConstraintViolationException(violations)
        }
    }
}
