package org.rookedsysc.membershipservice.application.port.`in`.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.membershipservice.common.LogConfig
import org.rookedsysc.membershipservice.common.ValidationUtils

data class ModifyMembershipCommand(
    @field:NotNull(message = "ID must not be null")
    val id: Long,

    val name: String?,

    val address: String?,

    val email: String?,

    val isCorp: Boolean?
) {
    companion object {
        private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    }

    init {
        ValidationUtils.validate(this.id)
        validEmail(email)
    }

    private fun validEmail(email: String?): Unit {
        if (email != null && !email.matches(emailRegex)) {
            throw IllegalArgumentException("Invalid email format")
        }
    }
}
