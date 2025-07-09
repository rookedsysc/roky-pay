package org.rookedsysc.moneyservice.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Valid
import org.rookedsysc.moneyservice.common.ValidationUtils
import java.time.LocalDateTime

/**
 * 머니 거래 이력 도메인 모델
 * 머니 거래 내역을 관리하는 도메인 엔티티
 */
class MoneyTransactionHistory(
    @field:NotBlank
    val transactionId: String,
    
    @field:NotBlank
    val memberId: String,
    
    @field:NotNull
    val transactionType: MoneyChangingType,
    
    @field:NotNull
    @field:Valid
    val transactionAmount: MoneyAmount,
    
    @field:NotNull
    @field:Valid
    val balanceAfterTransaction: MoneyAmount,
    
    @field:NotBlank
    val description: String,
    
    @field:NotNull
    val transactionDateTime: LocalDateTime,
    
    @field:NotBlank
    val moneyChangingRequestId: String
) {
    init {
        ValidationUtils.validate(this)
    }
    
    /**
     * 충전 거래인지 확인
     * @return 충전 거래인 경우 true, 아닌 경우 false
     */
    fun isChargeTransaction(): Boolean {
        return this.transactionType == MoneyChangingType.CHARGE
    }
    
    /**
     * 사용 거래인지 확인
     * @return 사용 거래인 경우 true, 아닌 경우 false
     */
    fun isUseTransaction(): Boolean {
        return this.transactionType == MoneyChangingType.USE
    }
    
    /**
     * 거래 요약 정보를 문자열로 반환
     * @return 거래 요약 정보
     */
    fun getTransactionSummary(): String {
        val typeText = when (transactionType) {
            MoneyChangingType.CHARGE -> "충전"
            MoneyChangingType.USE -> "사용"
        }
        return "$typeText: ${transactionAmount.format()} (잔액: ${balanceAfterTransaction.format()})"
    }
}