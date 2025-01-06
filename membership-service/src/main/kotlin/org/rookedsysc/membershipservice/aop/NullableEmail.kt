package org.rookedsysc.membershipservice.aop

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [NullableEmailValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NullableEmail(
    val message: String = "Invalid email format", // 메시지 속성 추가
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class NullableEmailValidator : ConstraintValidator<NullableEmail, String?> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return value == null || Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matches(value)
    }
}
