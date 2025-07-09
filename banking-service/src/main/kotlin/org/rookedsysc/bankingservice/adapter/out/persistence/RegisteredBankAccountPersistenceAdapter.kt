package org.rookedsysc.bankingservice.adapter.out.persistence

import org.rookedsysc.bankingservice.application.port.out.RegisterBankAccountPort
import org.rookedsysc.bankingservice.domain.RegisteredBankAccount
import org.rookedsysc.common.PersistenceAdapter

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    private val registeredBankAccountJpaRepository: RegisteredBankAccountJpaRepository,
    private val registeredBankAccountMapper: RegisteredBankAccountMapper
) : RegisterBankAccountPort {
    
    override fun createRegisteredBankAccount(registeredBankAccount: RegisteredBankAccount): RegisteredBankAccount {
        val jpaEntity = registeredBankAccountMapper.toJpaEntity(registeredBankAccount)
        val savedEntity = registeredBankAccountJpaRepository.save(jpaEntity)
        return registeredBankAccountMapper.toDomainEntity(savedEntity)
    }
    
    override fun findByMembershipId(membershipId: String): RegisteredBankAccount? {
        val jpaEntity = registeredBankAccountJpaRepository.findByMembershipId(membershipId)
        return jpaEntity?.let { registeredBankAccountMapper.toDomainEntity(it) }
    }
    
    override fun findByBankAccountNumber(bankAccountNumber: String): RegisteredBankAccount? {
        val jpaEntity = registeredBankAccountJpaRepository.findByBankAccountNumber(bankAccountNumber)
        return jpaEntity?.let { registeredBankAccountMapper.toDomainEntity(it) }
    }
}