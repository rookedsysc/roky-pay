package org.roky.pay.money.adapter.`in`.web

import org.roky.pay.money.domain.MoneyChangingRequest
import org.roky.pay.money.domain.MoneyChangingStatus
import org.roky.pay.money.domain.MoneyChangingType

data class MoneyChangingResponse(
    val moneyChangingRequestId: String,
    val targetMembershipId: String,
    val moneyChangingType: MoneyChangingType,
    val moneyChangingAmount: Int,
    val moneyChangingStatus: MoneyChangingStatus,
    val uuid: String,
    val createdAt: Long,
) {
    companion object {
        fun fromDomain(moneyChangingRequest: MoneyChangingRequest): MoneyChangingResponse {
            return MoneyChangingResponse(
                moneyChangingRequestId = moneyChangingRequest.moneyChangingRequestId.value,
                targetMembershipId = moneyChangingRequest.targetMembershipId.value,
                moneyChangingType = moneyChangingRequest.moneyChangingType,
                moneyChangingAmount = moneyChangingRequest.moneyChangingAmount.value.toInt(),
                moneyChangingStatus = moneyChangingRequest.moneyChangingStatus,
                uuid = moneyChangingRequest.uuid.value,
                createdAt = moneyChangingRequest.createdAt.value,
            )
        }
    }
}
