package org.roky.pay.money.domain

data class Money(
    val moneyId: MoneyId,
    val membershipId: MembershipId,
    val balance: Balance,
)

@JvmInline
value class MoneyId(val value: String)

@JvmInline
value class MembershipId(val value: String)

@JvmInline
value class Balance(val value: Long)
