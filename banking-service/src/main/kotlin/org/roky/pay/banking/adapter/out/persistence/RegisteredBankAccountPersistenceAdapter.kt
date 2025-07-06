package org.roky.pay.banking.adapter.out.persistence

import org.roky.pay.common.PersistenceAdapter
import org.roky.pay.banking.application.port.out.RegisterBankAccountPort
import org.roky.pay.banking.domain.RegisteredBankAccount

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    private val registeredBankAccountRepository: SpringDataRegisteredBankAccountRepository,
    private val registeredBankAccountMapper: RegisteredBankAccountMapper,
) : RegisterBankAccountPort {

    override fun createRegisteredBankAccount(
        membershipId: String,
        bankName: String,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean,
    ): RegisteredBankAccount {
        val entity = RegisteredBankAccountJpaEntity(
            membershipId = membershipId,
            bankName = bankName,
            bankAccountNumber = bankAccountNumber,
            linkedStatusIsValid = linkedStatusIsValid,
        )
        val savedEntity = registeredBankAccountRepository.save(entity)
        return registeredBankAccountMapper.mapToDomainEntity(savedEntity)
    }
}
