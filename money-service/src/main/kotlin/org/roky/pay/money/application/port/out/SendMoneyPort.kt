package org.roky.pay.money.application.port.out

interface SendMoneyPort {
    fun sendMoney(membershipId: String, amount: Int): Boolean
}
