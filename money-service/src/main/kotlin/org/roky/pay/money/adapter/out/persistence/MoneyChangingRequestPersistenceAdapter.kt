package org.roky.pay.money.adapter.out.persistence

import org.roky.pay.common.PersistenceAdapter
import org.roky.pay.money.application.port.out.MoneyChangingRequestPort
import org.roky.pay.money.domain.CreatedAt
import org.roky.pay.money.domain.MoneyChangingAmount
import org.roky.pay.money.domain.MoneyChangingRequest
import org.roky.pay.money.domain.MoneyChangingRequestId
import org.roky.pay.money.domain.MoneyChangingStatus
import org.roky.pay.money.domain.MoneyChangingType
import org.roky.pay.money.domain.TargetMembershipId
import org.roky.pay.money.domain.Uuid
import java.time.LocalDateTime

@PersistenceAdapter
class MoneyChangingRequestPersistenceAdapter(
    private val moneyChangingRequestRepository: SpringDataMoneyChangingRequestRepository,
) : MoneyChangingRequestPort {
    override fun createMoneyChangingRequest(
        targetMembershipId: String,
        moneyChangingType: MoneyChangingType,
        moneyChangingAmount: Int,
        moneyChangingStatus: MoneyChangingStatus,
        uuid: String,
    ): MoneyChangingRequest {
        val entity = MoneyChangingRequestJpaEntity(
            targetMembershipId = targetMembershipId,
            moneyChangingType = moneyChangingType.ordinal,
            moneyChangingAmount = moneyChangingAmount,
            moneyChangingStatus = moneyChangingStatus.ordinal,
            uuid = uuid,
            createdAt = LocalDateTime.now(),
        )
        val savedEntity = moneyChangingRequestRepository.save(entity)
        return mapToDomainEntity(savedEntity)
    }

    override fun modifyMoneyChangingRequest(
        moneyChangingRequestId: String,
        moneyChangingStatus: MoneyChangingStatus,
    ): MoneyChangingRequest {
        val entity = moneyChangingRequestRepository.findById(moneyChangingRequestId.toLong())
            .orElseThrow { NoSuchElementException("MoneyChangingRequest not found") }

        val updatedEntity = entity.copy(
            moneyChangingStatus = moneyChangingStatus.ordinal,
        )
        val savedEntity = moneyChangingRequestRepository.save(updatedEntity)
        return mapToDomainEntity(savedEntity)
    }

    private fun mapToDomainEntity(entity: MoneyChangingRequestJpaEntity): MoneyChangingRequest {
        return MoneyChangingRequest(
            moneyChangingRequestId = MoneyChangingRequestId(entity.moneyChangingRequestId.toString()),
            targetMembershipId = TargetMembershipId(entity.targetMembershipId),
            moneyChangingType = MoneyChangingType.values()[entity.moneyChangingType],
            moneyChangingAmount = MoneyChangingAmount(entity.moneyChangingAmount.toLong()),
            moneyChangingStatus = MoneyChangingStatus.values()[entity.moneyChangingStatus],
            uuid = Uuid(entity.uuid),
            createdAt = CreatedAt(entity.createdAt.toEpochSecond(java.time.ZoneOffset.UTC)),
        )
    }
}
