package org.rookedsysc.moneyservice.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import org.rookedsysc.moneyservice.common.ValidationUtils
import java.math.BigDecimal

class MemberMoney(
    @field:NotBlank
    val memberMoneyId: String,
    
    @field:NotBlank
    val membershipId: String,
    
    @field:NotNull
    @field:PositiveOrZero
    val balance: BigDecimal
) {
    init {
        ValidationUtils.validate(this)
    }
    
    fun chargeBalance(amount: BigDecimal): MemberMoney {
        require(amount > BigDecimal.ZERO) { "충전 금액은 0보다 커야 합니다." }
        
        return MemberMoney(
            memberMoneyId = this.memberMoneyId,
            membershipId = this.membershipId,
            balance = this.balance.add(amount)
        )
    }
    
    fun useBalance(amount: BigDecimal): MemberMoney {
        require(amount > BigDecimal.ZERO) { "사용 금액은 0보다 커야 합니다." }
        require(this.balance >= amount) { "잔액이 부족합니다. 현재 잔액: ${this.balance}, 사용 요청 금액: $amount" }
        
        return MemberMoney(
            memberMoneyId = this.memberMoneyId,
            membershipId = this.membershipId,
            balance = this.balance.subtract(amount)
        )
    }
    
    fun hasEnoughBalance(amount: BigDecimal): Boolean {
        return this.balance >= amount
    }
}