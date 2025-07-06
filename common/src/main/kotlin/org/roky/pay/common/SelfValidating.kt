package org.roky.pay.common

import jakarta.validation.*
import java.util.Set

abstract class SelfValidating<T> {

    private val validator: Validator

    init {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    protected fun validateSelf() {
        val violations = validator.validate(this as T) as java.util.Set<ConstraintViolation<T>>
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations.toSet())
        }
    }
} 