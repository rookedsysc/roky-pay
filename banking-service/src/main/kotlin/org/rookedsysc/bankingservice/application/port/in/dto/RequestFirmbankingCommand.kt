package org.rookedsysc.bankingservice.application.port.`in`.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.rookedsysc.common.SelfValidating

class RequestFirmbankingCommand(
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
) : SelfValidating<RequestFirmbankingCommand>() {
    
    init {
        this.validateSelf()
    }
}