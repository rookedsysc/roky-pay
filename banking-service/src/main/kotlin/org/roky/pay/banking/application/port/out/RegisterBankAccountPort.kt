package org.roky.pay.banking.application.port.out

import org.roky.pay.banking.domain.RegisteredBankAccount
// import org.roky.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity

interface RegisterBankAccountPort {
    fun createRegisteredBankAccount(
        membershipId: String,
        bankName: String,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean,
    ): RegisteredBankAccount
}
