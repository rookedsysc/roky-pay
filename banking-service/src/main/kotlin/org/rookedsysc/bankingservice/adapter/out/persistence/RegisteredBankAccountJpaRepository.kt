package org.rookedsysc.bankingservice.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RegisteredBankAccountJpaRepository : JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    
    @Query("SELECT r FROM RegisteredBankAccountJpaEntity r WHERE r.membershipId = :membershipId")
    fun findByMembershipId(membershipId: String): RegisteredBankAccountJpaEntity?
    
    @Query("SELECT r FROM RegisteredBankAccountJpaEntity r WHERE r.bankAccountNumber = :bankAccountNumber")
    fun findByBankAccountNumber(bankAccountNumber: String): RegisteredBankAccountJpaEntity?
}