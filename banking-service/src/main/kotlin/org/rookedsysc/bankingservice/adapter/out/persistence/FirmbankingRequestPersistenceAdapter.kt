package org.rookedsysc.bankingservice.adapter.out.persistence

import org.rookedsysc.bankingservice.application.port.out.RequestFirmbankingPort
import org.rookedsysc.bankingservice.domain.FirmbankingRequest
import org.rookedsysc.common.PersistenceAdapter

@PersistenceAdapter
class FirmbankingRequestPersistenceAdapter(
    private val firmbankingRequestJpaRepository: FirmbankingRequestJpaRepository,
    private val firmbankingRequestMapper: FirmbankingRequestMapper
) : RequestFirmbankingPort {
    
    override fun createFirmbankingRequest(firmbankingRequest: FirmbankingRequest): FirmbankingRequest {
        val jpaEntity = firmbankingRequestMapper.toJpaEntity(firmbankingRequest)
        val savedEntity = firmbankingRequestJpaRepository.save(jpaEntity)
        return firmbankingRequestMapper.toDomainEntity(savedEntity)
    }
    
    override fun updateFirmbankingRequest(firmbankingRequest: FirmbankingRequest): FirmbankingRequest {
        val jpaEntity = firmbankingRequestMapper.toJpaEntity(firmbankingRequest)
        val savedEntity = firmbankingRequestJpaRepository.save(jpaEntity)
        return firmbankingRequestMapper.toDomainEntity(savedEntity)
    }
    
    override fun findById(id: String): FirmbankingRequest? {
        val jpaEntity = firmbankingRequestJpaRepository.findById(id.toLong())
        return if (jpaEntity.isPresent) {
            firmbankingRequestMapper.toDomainEntity(jpaEntity.get())
        } else {
            null
        }
    }
}