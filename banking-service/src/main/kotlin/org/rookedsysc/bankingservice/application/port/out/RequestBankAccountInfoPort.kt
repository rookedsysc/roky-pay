package org.rookedsysc.bankingservice.application.port.out

import org.rookedsysc.bankingservice.adapter.out.external.bank.BankAccount
import org.rookedsysc.bankingservice.adapter.out.external.bank.GetBankAccountRequest

interface RequestBankAccountInfoPort {
    fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount
}