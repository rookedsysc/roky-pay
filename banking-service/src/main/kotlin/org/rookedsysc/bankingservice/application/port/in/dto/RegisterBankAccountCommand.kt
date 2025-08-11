package org.rookedsysc.bankingservice.application.port.`in`.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.common.SelfValidating

class RegisterBankAccountCommand(
    @field:NotBlank
    val membershipId: String,
    
    @field:NotBlank
    val bankName: String,
    
    @field:NotBlank
    val bankAccountNumber: String,
    
    @field:NotNull
    val isValid: Boolean
) : SelfValidating<RegisterBankAccountCommand>() {
    
    init {
        this.validateSelf()
    }
}