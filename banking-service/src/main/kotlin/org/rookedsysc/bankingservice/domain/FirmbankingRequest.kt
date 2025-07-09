package org.rookedsysc.bankingservice.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.rookedsysc.bankingservice.common.ValidationUtils
import java.util.*

class FirmbankingRequest(
    @field:NotBlank
    val firmbankingRequestId: String,
    
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
    val moneyAmount: Int,
    
    @field:NotNull
    val firmbankingStatus: FirmbankingStatus,
    
    @field:NotBlank
    val uuid: String
) {
    init {
        ValidationUtils.validate(this)
    }
    
    fun updateStatus(newStatus: FirmbankingStatus): FirmbankingRequest {
        return FirmbankingRequest(
            firmbankingRequestId = this.firmbankingRequestId,
            fromBankName = this.fromBankName,
            fromBankAccountNumber = this.fromBankAccountNumber,
            toBankName = this.toBankName,
            toBankAccountNumber = this.toBankAccountNumber,
            moneyAmount = this.moneyAmount,
            firmbankingStatus = newStatus,
            uuid = this.uuid
        )
    }
    
    fun isCompleted(): Boolean {
        return firmbankingStatus == FirmbankingStatus.COMPLETED || 
               firmbankingStatus == FirmbankingStatus.FAILED
    }
    
    fun isSuccess(): Boolean {
        return firmbankingStatus == FirmbankingStatus.COMPLETED
    }
    
    fun isPending(): Boolean {
        return firmbankingStatus == FirmbankingStatus.REQUESTED
    }
}

enum class FirmbankingStatus(val value: Int) {
    REQUESTED(0),
    COMPLETED(1),
    FAILED(2)
}