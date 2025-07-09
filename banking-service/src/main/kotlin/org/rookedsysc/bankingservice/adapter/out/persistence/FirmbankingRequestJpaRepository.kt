package org.rookedsysc.bankingservice.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FirmbankingRequestJpaRepository : JpaRepository<FirmbankingRequestJpaEntity, Long> {
    
    @Query("SELECT f FROM FirmbankingRequestJpaEntity f WHERE f.uuid = :uuid")
    fun findByUuid(uuid: String): FirmbankingRequestJpaEntity?
}