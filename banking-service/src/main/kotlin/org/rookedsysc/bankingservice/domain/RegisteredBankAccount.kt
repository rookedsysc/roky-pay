package org.rookedsysc.bankingservice.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.rookedsysc.bankingservice.common.ValidationUtils

class RegisteredBankAccount(
    @field:NotBlank
    val registeredBankAccountId: String,
    
    @field:NotBlank
    val membershipId: String,
    
    @field:NotBlank
    val bankName: String,
    
    @field:NotBlank
    val bankAccountNumber: String,
    
    @field:NotNull
    val linkedStatusIsValid: Boolean
) {
    init {
        ValidationUtils.validate(this)
    }
    
    fun isValid(): Boolean {
        return linkedStatusIsValid
    }
    
    fun activate(): RegisteredBankAccount {
        return RegisteredBankAccount(
            registeredBankAccountId = this.registeredBankAccountId,
            membershipId = this.membershipId,
            bankName = this.bankName,
            bankAccountNumber = this.bankAccountNumber,
            linkedStatusIsValid = true
        )
    }
    
    fun deactivate(): RegisteredBankAccount {
        return RegisteredBankAccount(
            registeredBankAccountId = this.registeredBankAccountId,
            membershipId = this.membershipId,
            bankName = this.bankName,
            bankAccountNumber = this.bankAccountNumber,
            linkedStatusIsValid = false
        )
    }
}