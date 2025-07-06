package org.roky.pay.money.domain

data class MoneyChangingRequest(
    val moneyChangingRequestId: MoneyChangingRequestId,
    val targetMembershipId: TargetMembershipId,
    val moneyChangingType: MoneyChangingType,
    val moneyChangingAmount: MoneyChangingAmount,
    val moneyChangingStatus: MoneyChangingStatus,
    val uuid: Uuid,
    val createdAt: CreatedAt,
)

@JvmInline
value class MoneyChangingRequestId(val value: String)

@JvmInline
value class TargetMembershipId(val value: String)

enum class MoneyChangingType {
    INCREASE,
    DECREASE
}

@JvmInline
value class MoneyChangingAmount(val value: Long)

enum class MoneyChangingStatus {
    REQUESTED,
    SUCCEEDED,
    FAILED
}

@JvmInline
value class Uuid(val value: String)

@JvmInline
value class CreatedAt(val value: Long)
