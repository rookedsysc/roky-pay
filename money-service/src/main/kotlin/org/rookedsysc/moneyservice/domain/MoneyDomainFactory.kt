package org.rookedsysc.moneyservice.domain

import org.rookedsysc.moneyservice.common.constant.MoneyConstants
import java.time.LocalDateTime
import java.util.*

/**
 * 머니 도메인 팩토리
 * 머니 관련 도메인 객체 생성을 위한 팩토리 클래스
 */
object MoneyDomainFactory {
    
    /**
     * 새로운 MemberMoney 생성
     * @param memberId 회원 ID
     * @param linkedBankAccount 연결된 은행 계좌
     * @param aggregateIdentifier 집합 식별자
     * @return 새로운 MemberMoney 객체
     */
    fun createMemberMoney(
        memberId: String,
        linkedBankAccount: String,
        aggregateIdentifier: String
    ): MemberMoney {
        return MemberMoney(
            memberMoneyId = UUID.randomUUID().toString(),
            memberId = memberId,
            balance = MoneyAmount.of(MoneyConstants.DEFAULT_BALANCE),
            linkedBankAccount = linkedBankAccount,
            aggregateIdentifier = aggregateIdentifier
        )
    }
    
    /**
     * 새로운 MoneyChangingRequest 생성
     * @param targetMembershipId 대상 회원 ID
     * @param changingType 변경 타입
     * @param changingMoneyAmount 변경 금액
     * @return 새로운 MoneyChangingRequest 객체
     */
    fun createMoneyChangingRequest(
        targetMembershipId: String,
        changingType: MoneyChangingType,
        changingMoneyAmount: MoneyAmount
    ): MoneyChangingRequest {
        return MoneyChangingRequest(
            moneyChangingRequestId = UUID.randomUUID().toString(),
            targetMembershipId = targetMembershipId,
            changingType = changingType,
            changingMoneyAmount = changingMoneyAmount,
            changingMoneyStatus = MoneyChangingStatus.PENDING,
            uuid = UUID.randomUUID().toString(),
            createdAt = LocalDateTime.now()
        )
    }
    
    /**
     * 새로운 MoneyTransactionHistory 생성
     * @param memberId 회원 ID
     * @param transactionType 거래 타입
     * @param transactionAmount 거래 금액
     * @param balanceAfterTransaction 거래 후 잔액
     * @param description 거래 설명
     * @param moneyChangingRequestId 머니 변경 요청 ID
     * @return 새로운 MoneyTransactionHistory 객체
     */
    fun createMoneyTransactionHistory(
        memberId: String,
        transactionType: MoneyChangingType,
        transactionAmount: MoneyAmount,
        balanceAfterTransaction: MoneyAmount,
        description: String,
        moneyChangingRequestId: String
    ): MoneyTransactionHistory {
        return MoneyTransactionHistory(
            transactionId = UUID.randomUUID().toString(),
            memberId = memberId,
            transactionType = transactionType,
            transactionAmount = transactionAmount,
            balanceAfterTransaction = balanceAfterTransaction,
            description = description,
            transactionDateTime = LocalDateTime.now(),
            moneyChangingRequestId = moneyChangingRequestId
        )
    }
}