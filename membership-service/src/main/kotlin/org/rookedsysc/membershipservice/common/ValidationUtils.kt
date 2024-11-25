package org.rookedsysc.membershipservice.common

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory

object ValidationUtils {
    private val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    private val validator: Validator = factory.validator

    fun <T> validate(target: T) {
        val violations = validator.validate(target)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
