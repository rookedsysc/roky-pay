package org.rookedsysc.bankingservice.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterBankAccountRequest(
    @field:NotBlank
    val membershipId: String,
    
    @field:NotBlank
    val bankName: String,
    
    @field:NotBlank
    val bankAccountNumber: String,
    
    @field:NotNull
    val isValid: Boolean
)