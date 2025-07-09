package org.rookedsysc.moneyservice.adapter.out.persistence

import org.rookedsysc.common.PersistenceAdapter
import org.rookedsysc.moneyservice.application.port.out.DecreaseMoneyPort
import org.rookedsysc.moneyservice.application.port.out.IncreaseMoneyPort
import org.rookedsysc.moneyservice.domain.MemberMoney
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest
import org.rookedsysc.moneyservice.domain.MoneyChangingStatus
import org.rookedsysc.moneyservice.domain.MoneyChangingType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@PersistenceAdapter
class MoneyPersistenceAdapter(
    private val memberMoneyJpaRepository: MemberMoneyJpaRepository,
    private val moneyChangingRequestJpaRepository: MoneyChangingRequestJpaRepository,
    private val moneyMapper: MoneyMapper
) : IncreaseMoneyPort, DecreaseMoneyPort {
    
    override fun createMoneyChangingRequest(
        targetMembershipId: String,
        amount: BigDecimal,
        changingType: Int
    ): MoneyChangingRequest {
        val moneyChangingRequest = MoneyChangingRequest(
            moneyChangingRequestId = "0",
            targetMembershipId = targetMembershipId,
            changingType = if (changingType == 0) MoneyChangingType.INCREASE else MoneyChangingType.DECREASE,
            changingMoneyAmount = amount,
            changingMoneyStatus = MoneyChangingStatus.REQUESTED,
            uuid = UUID.randomUUID().toString(),
            createdAt = LocalDateTime.now()
        )
        
        val jpaEntity = moneyMapper.toJpaEntity(moneyChangingRequest)
        val savedEntity = moneyChangingRequestJpaRepository.save(jpaEntity)
        
        return moneyMapper.toDomainEntity(savedEntity)
    }
    
    override fun increaseMoney(membershipId: String, amount: BigDecimal): MemberMoney {
        val existingMemberMoney = memberMoneyJpaRepository.findByMembershipId(membershipId)
        
        val updatedMemberMoney = if (existingMemberMoney != null) {
            val domainEntity = moneyMapper.toDomainEntity(existingMemberMoney)
            domainEntity.chargeBalance(amount)
        } else {
            MemberMoney(
                memberMoneyId = "0",
                membershipId = membershipId,
                balance = amount
            )
        }
        
        val jpaEntity = moneyMapper.toJpaEntity(updatedMemberMoney)
        val savedEntity = memberMoneyJpaRepository.save(jpaEntity)
        
        return moneyMapper.toDomainEntity(savedEntity)
    }
    
    override fun decreaseMoney(membershipId: String, amount: BigDecimal): MemberMoney {
        val existingMemberMoney = memberMoneyJpaRepository.findByMembershipId(membershipId)
            ?: throw IllegalArgumentException("회원 머니 정보가 존재하지 않습니다.")
        
        val domainEntity = moneyMapper.toDomainEntity(existingMemberMoney)
        val updatedMemberMoney = domainEntity.useBalance(amount)
        
        val jpaEntity = moneyMapper.toJpaEntity(updatedMemberMoney)
        val savedEntity = memberMoneyJpaRepository.save(jpaEntity)
        
        return moneyMapper.toDomainEntity(savedEntity)
    }
    
    override fun getMemberMoney(membershipId: String): MemberMoney? {
        val jpaEntity = memberMoneyJpaRepository.findByMembershipId(membershipId)
        return jpaEntity?.let { moneyMapper.toDomainEntity(it) }
    }
    
    override fun updateMoneyChangingRequest(moneyChangingRequest: MoneyChangingRequest): MoneyChangingRequest {
        val jpaEntity = moneyMapper.toJpaEntity(moneyChangingRequest)
        val savedEntity = moneyChangingRequestJpaRepository.save(jpaEntity)
        
        return moneyMapper.toDomainEntity(savedEntity)
    }
}