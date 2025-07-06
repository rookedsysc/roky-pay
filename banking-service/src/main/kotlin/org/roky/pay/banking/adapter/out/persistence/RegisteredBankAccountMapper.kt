package org.roky.pay.banking.adapter.out.persistence

import org.roky.pay.banking.domain.RegisteredBankAccount
import org.roky.pay.banking.domain.RegisteredBankAccountId
import org.springframework.stereotype.Component

@Component
class RegisteredBankAccountMapper {
    fun mapToDomainEntity(jpaEntity: RegisteredBankAccountJpaEntity): RegisteredBankAccount {
        return RegisteredBankAccount.generateRegisteredBankAccount(
            RegisteredBankAccountId(jpaEntity.id.toString())
        )
    }
}
