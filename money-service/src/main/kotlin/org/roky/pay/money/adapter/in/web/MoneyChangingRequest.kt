package org.roky.pay.money.adapter.`in`.web

data class MoneyChangingRequest(
    val targetMembershipId: String,
    val amount: Int,
)
