package org.roky.pay.banking.application.port.`in`

import org.roky.pay.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterBankAccountCommand(
    @field:NotNull
    val membershipId: String,
    @field:NotNull
    val bankName: String,
    @field:NotNull
    val bankAccountNumber: String,
    @field:AssertTrue
    val linkedStatusIsValid: Boolean,
) : SelfValidating<RegisterBankAccountCommand>() {
    init {
        this.validateSelf()
    }
}
