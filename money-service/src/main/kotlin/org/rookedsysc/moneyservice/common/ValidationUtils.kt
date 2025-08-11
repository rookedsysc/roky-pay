package org.rookedsysc.moneyservice.common

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

object ValidationUtils {
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator
    
    fun <T> validate(obj: T) {
        val violations: Set<ConstraintViolation<T>> = validator.validate(obj)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}