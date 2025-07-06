package org.roky.pay.money.application.port.out

import org.roky.pay.money.domain.MoneyChangingRequest
import org.roky.pay.money.domain.MoneyChangingStatus
import org.roky.pay.money.domain.MoneyChangingType

interface MoneyChangingRequestPort {
    fun createMoneyChangingRequest(
        targetMembershipId: String,
        moneyChangingType: MoneyChangingType,
        moneyChangingAmount: Int,
        moneyChangingStatus: MoneyChangingStatus,
        uuid: String,
    ): MoneyChangingRequest

    fun modifyMoneyChangingRequest(
        moneyChangingRequestId: String,
        moneyChangingStatus: MoneyChangingStatus,
    ): MoneyChangingRequest
}
