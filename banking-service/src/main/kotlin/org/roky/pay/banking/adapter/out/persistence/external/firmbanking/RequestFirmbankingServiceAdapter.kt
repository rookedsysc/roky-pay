package org.roky.pay.banking.adapter.out.persistence.external.firmbanking

import org.roky.pay.banking.application.port.out.GetExternalBankAccountInfoPort
import org.roky.pay.banking.domain.BankAccount
import org.roky.pay.common.ServiceAdapter

@ServiceAdapter
class RequestFirmbankingServiceAdapter : GetExternalBankAccountInfoPort {
    override fun getExternalBankAccountInfo(bankAccount: BankAccount): GetExternalBankAccountInfoResponse {
        // TODO: Implement actual external bank account info retrieval logic
        return GetExternalBankAccountInfoResponse()
    }
}
