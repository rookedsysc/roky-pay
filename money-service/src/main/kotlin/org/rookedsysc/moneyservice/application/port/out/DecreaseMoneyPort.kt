package org.rookedsysc.moneyservice.application.port.out

import org.rookedsysc.moneyservice.domain.MemberMoney
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest
import java.math.BigDecimal

interface DecreaseMoneyPort {
    fun createMoneyChangingRequest(
        targetMembershipId: String,
        amount: BigDecimal,
        changingType: Int
    ): MoneyChangingRequest
    
    fun decreaseMoney(membershipId: String, amount: BigDecimal): MemberMoney
    
    fun getMemberMoney(membershipId: String): MemberMoney?
    
    fun updateMoneyChangingRequest(moneyChangingRequest: MoneyChangingRequest): MoneyChangingRequest
}