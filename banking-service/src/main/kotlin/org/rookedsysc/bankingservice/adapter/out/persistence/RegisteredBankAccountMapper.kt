package org.rookedsysc.bankingservice.adapter.out.persistence

import org.rookedsysc.bankingservice.domain.RegisteredBankAccount
import org.springframework.stereotype.Component

@Component
class RegisteredBankAccountMapper {
    
    fun toDomainEntity(jpaEntity: RegisteredBankAccountJpaEntity): RegisteredBankAccount {
        return RegisteredBankAccount(
            registeredBankAccountId = jpaEntity.registeredBankAccountId.toString(),
            membershipId = jpaEntity.membershipId,
            bankName = jpaEntity.bankName,
            bankAccountNumber = jpaEntity.bankAccountNumber,
            linkedStatusIsValid = jpaEntity.linkedStatusIsValid
        )
    }
    
    fun toJpaEntity(registeredBankAccount: RegisteredBankAccount): RegisteredBankAccountJpaEntity {
        return RegisteredBankAccountJpaEntity(
            registeredBankAccountId = if (registeredBankAccount.registeredBankAccountId == "0") 0 else registeredBankAccount.registeredBankAccountId.toLong(),
            membershipId = registeredBankAccount.membershipId,
            bankName = registeredBankAccount.bankName,
            bankAccountNumber = registeredBankAccount.bankAccountNumber,
            linkedStatusIsValid = registeredBankAccount.linkedStatusIsValid
        )
    }
}