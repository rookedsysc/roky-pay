package org.rookedsysc.bankingservice.application.port.out

import org.rookedsysc.bankingservice.domain.RegisteredBankAccount

interface RegisterBankAccountPort {
    fun createRegisteredBankAccount(registeredBankAccount: RegisteredBankAccount): RegisteredBankAccount
    fun findByMembershipId(membershipId: String): RegisteredBankAccount?
    fun findByBankAccountNumber(bankAccountNumber: String): RegisteredBankAccount?
}