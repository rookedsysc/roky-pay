package org.rookedsysc.moneyservice.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import org.rookedsysc.moneyservice.common.ValidationUtils
import java.math.BigDecimal
import java.time.LocalDateTime

class MoneyChangingRequest(
    @field:NotBlank
    val moneyChangingRequestId: String,
    
    @field:NotBlank
    val targetMembershipId: String,
    
    @field:NotNull
    val changingType: MoneyChangingType,
    
    @field:NotNull
    @field:PositiveOrZero
    val changingMoneyAmount: BigDecimal,
    
    @field:NotNull
    val changingMoneyStatus: MoneyChangingStatus,
    
    @field:NotBlank
    val uuid: String,
    
    @field:NotNull
    val createdAt: LocalDateTime
) {
    init {
        ValidationUtils.validate(this)
    }
    
    fun updateStatus(newStatus: MoneyChangingStatus): MoneyChangingRequest {
        return MoneyChangingRequest(
            moneyChangingRequestId = this.moneyChangingRequestId,
            targetMembershipId = this.targetMembershipId,
            changingType = this.changingType,
            changingMoneyAmount = this.changingMoneyAmount,
            changingMoneyStatus = newStatus,
            uuid = this.uuid,
            createdAt = this.createdAt
        )
    }
    
    fun isCompleted(): Boolean {
        return changingMoneyStatus == MoneyChangingStatus.SUCCESS || 
               changingMoneyStatus == MoneyChangingStatus.FAILED
    }
    
    fun isSuccess(): Boolean {
        return changingMoneyStatus == MoneyChangingStatus.SUCCESS
    }
    
    fun isPending(): Boolean {
        return changingMoneyStatus == MoneyChangingStatus.REQUESTED
    }
    
    fun isIncrease(): Boolean {
        return changingType == MoneyChangingType.INCREASE
    }
    
    fun isDecrease(): Boolean {
        return changingType == MoneyChangingType.DECREASE
    }
}

enum class MoneyChangingType(val value: Int) {
    INCREASE(0),
    DECREASE(1)
}

enum class MoneyChangingStatus(val value: Int) {
    REQUESTED(0),
    SUCCESS(1),
    FAILED(2)
}