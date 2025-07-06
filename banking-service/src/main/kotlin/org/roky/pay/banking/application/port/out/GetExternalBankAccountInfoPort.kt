package org.roky.pay.banking.application.port.out

import org.roky.pay.banking.domain.BankAccount
// import org.roky.pay.banking.adapter.out.persistence.external.firmbanking.GetExternalBankAccountInfoResponse

interface GetExternalBankAccountInfoPort {
    fun getExternalBankAccountInfo(bankAccount: BankAccount): Any // Replace Any with GetExternalBankAccountInfoResponse
}
