package org.rookedsysc.bankingservice.adapter.out.persistence

import org.rookedsysc.bankingservice.domain.FirmbankingRequest
import org.rookedsysc.bankingservice.domain.FirmbankingStatus
import org.springframework.stereotype.Component

@Component
class FirmbankingRequestMapper {
    
    fun toDomainEntity(jpaEntity: FirmbankingRequestJpaEntity): FirmbankingRequest {
        return FirmbankingRequest(
            firmbankingRequestId = jpaEntity.firmbankingRequestId.toString(),
            fromBankName = jpaEntity.fromBankName,
            fromBankAccountNumber = jpaEntity.fromBankAccountNumber,
            toBankName = jpaEntity.toBankName,
            toBankAccountNumber = jpaEntity.toBankAccountNumber,
            moneyAmount = jpaEntity.moneyAmount,
            firmbankingStatus = when (jpaEntity.firmbankingStatus) {
                0 -> FirmbankingStatus.REQUESTED
                1 -> FirmbankingStatus.COMPLETED
                2 -> FirmbankingStatus.FAILED
                else -> throw IllegalArgumentException("Invalid firmbanking status: ${jpaEntity.firmbankingStatus}")
            },
            uuid = jpaEntity.uuid
        )
    }
    
    fun toJpaEntity(firmbankingRequest: FirmbankingRequest): FirmbankingRequestJpaEntity {
        return FirmbankingRequestJpaEntity(
            firmbankingRequestId = if (firmbankingRequest.firmbankingRequestId == "0") 0 else firmbankingRequest.firmbankingRequestId.toLong(),
            fromBankName = firmbankingRequest.fromBankName,
            fromBankAccountNumber = firmbankingRequest.fromBankAccountNumber,
            toBankName = firmbankingRequest.toBankName,
            toBankAccountNumber = firmbankingRequest.toBankAccountNumber,
            moneyAmount = firmbankingRequest.moneyAmount,
            firmbankingStatus = firmbankingRequest.firmbankingStatus.value,
            uuid = firmbankingRequest.uuid
        )
    }
}