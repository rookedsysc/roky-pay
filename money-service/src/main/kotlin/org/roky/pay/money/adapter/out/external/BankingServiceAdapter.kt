package org.roky.pay.money.adapter.out.external

import org.roky.pay.common.ServiceAdapter
import org.roky.pay.money.application.port.out.GetBankingPort

@ServiceAdapter
class BankingServiceAdapter : GetBankingPort {
    override fun getBankAccountStatus(bankAccountId: String): Boolean {
        // TODO: Implement actual banking service call
        return true
    }
}
