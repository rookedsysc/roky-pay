package org.roky.pay.money.application.port.out

interface GetBankingPort {
    fun getBankAccountStatus(bankAccountId: String): Boolean
}
