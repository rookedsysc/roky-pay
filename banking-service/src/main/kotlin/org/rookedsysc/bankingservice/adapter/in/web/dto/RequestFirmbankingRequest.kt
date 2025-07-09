package org.rookedsysc.bankingservice.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class RequestFirmbankingRequest(
    @field:NotBlank
    val fromBankName: String,
    
    @field:NotBlank
    val fromBankAccountNumber: String,
    
    @field:NotBlank
    val toBankName: String,
    
    @field:NotBlank
    val toBankAccountNumber: String,
    
    @field:NotNull
    @field:Positive
    val moneyAmount: Int
)