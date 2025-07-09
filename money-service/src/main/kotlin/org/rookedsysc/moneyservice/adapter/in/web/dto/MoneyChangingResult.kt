package org.rookedsysc.moneyservice.adapter.`in`.web.dto

import org.rookedsysc.moneyservice.domain.MoneyChangingRequest
import java.math.BigDecimal

data class MoneyChangingResult(
    val moneyChangingRequestId: String,
    val moneyChangingType: Int,
    val moneyChangingResultStatus: Int,
    val amount: BigDecimal
) {
    companion object {
        fun from(moneyChangingRequest: MoneyChangingRequest): MoneyChangingResult {
            return MoneyChangingResult(
                moneyChangingRequestId = moneyChangingRequest.moneyChangingRequestId,
                moneyChangingType = moneyChangingRequest.changingType.value,
                moneyChangingResultStatus = moneyChangingRequest.changingMoneyStatus.value,
                amount = moneyChangingRequest.changingMoneyAmount
            )
        }
    }
}