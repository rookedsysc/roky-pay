package org.rookedsysc.moneyservice.adapter.out.persistence

import org.rookedsysc.moneyservice.domain.MemberMoney
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest
import org.rookedsysc.moneyservice.domain.MoneyChangingStatus
import org.rookedsysc.moneyservice.domain.MoneyChangingType
import org.springframework.stereotype.Component

@Component
class MoneyMapper {
    
    fun toDomainEntity(jpaEntity: MemberMoneyJpaEntity): MemberMoney {
        return MemberMoney(
            memberMoneyId = jpaEntity.memberMoneyId.toString(),
            membershipId = jpaEntity.membershipId,
            balance = jpaEntity.balance
        )
    }
    
    fun toJpaEntity(memberMoney: MemberMoney): MemberMoneyJpaEntity {
        return MemberMoneyJpaEntity(
            memberMoneyId = if (memberMoney.memberMoneyId == "0") 0 else memberMoney.memberMoneyId.toLong(),
            membershipId = memberMoney.membershipId,
            balance = memberMoney.balance
        )
    }
    
    fun toDomainEntity(jpaEntity: MoneyChangingRequestJpaEntity): MoneyChangingRequest {
        return MoneyChangingRequest(
            moneyChangingRequestId = jpaEntity.moneyChangingRequestId.toString(),
            targetMembershipId = jpaEntity.targetMembershipId,
            changingType = when (jpaEntity.moneyChangingType) {
                0 -> MoneyChangingType.INCREASE
                1 -> MoneyChangingType.DECREASE
                else -> throw IllegalArgumentException("Invalid money changing type: ${jpaEntity.moneyChangingType}")
            },
            changingMoneyAmount = jpaEntity.moneyAmount,
            changingMoneyStatus = when (jpaEntity.changingMoneyStatus) {
                0 -> MoneyChangingStatus.REQUESTED
                1 -> MoneyChangingStatus.SUCCESS
                2 -> MoneyChangingStatus.FAILED
                else -> throw IllegalArgumentException("Invalid money changing status: ${jpaEntity.changingMoneyStatus}")
            },
            uuid = jpaEntity.uuid,
            createdAt = jpaEntity.createdAt
        )
    }
    
    fun toJpaEntity(moneyChangingRequest: MoneyChangingRequest): MoneyChangingRequestJpaEntity {
        return MoneyChangingRequestJpaEntity(
            moneyChangingRequestId = if (moneyChangingRequest.moneyChangingRequestId == "0") 0 else moneyChangingRequest.moneyChangingRequestId.toLong(),
            targetMembershipId = moneyChangingRequest.targetMembershipId,
            moneyChangingType = moneyChangingRequest.changingType.value,
            moneyAmount = moneyChangingRequest.changingMoneyAmount,
            changingMoneyStatus = moneyChangingRequest.changingMoneyStatus.value,
            uuid = moneyChangingRequest.uuid,
            createdAt = moneyChangingRequest.createdAt
        )
    }
}