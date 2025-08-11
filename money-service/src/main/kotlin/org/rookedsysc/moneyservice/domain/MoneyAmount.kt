package org.rookedsysc.moneyservice.domain

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import org.rookedsysc.moneyservice.common.ValidationUtils
import org.rookedsysc.moneyservice.common.constant.MoneyConstants
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 머니 금액 값 객체 (Value Object)
 * 금액을 안전하게 다루기 위한 도메인 값 객체
 */
data class MoneyAmount(
    @field:NotNull
    @field:PositiveOrZero
    val amount: BigDecimal
) {
    init {
        ValidationUtils.validate(this)
        require(amount.scale() <= MoneyConstants.DECIMAL_PLACES) {
            "금액의 소수점 자리수는 ${MoneyConstants.DECIMAL_PLACES}자리를 초과할 수 없습니다."
        }
    }
    
    /**
     * 금액을 정규화 (소수점 자리수 조정)
     * @return 정규화된 MoneyAmount 객체
     */
    fun normalize(): MoneyAmount {
        return MoneyAmount(amount.setScale(MoneyConstants.DECIMAL_PLACES, RoundingMode.HALF_UP))
    }
    
    /**
     * 다른 MoneyAmount와 더하기
     * @param other 더할 MoneyAmount
     * @return 더한 결과 MoneyAmount
     */
    fun add(other: MoneyAmount): MoneyAmount {
        return MoneyAmount(this.amount.add(other.amount))
    }
    
    /**
     * 다른 MoneyAmount와 빼기
     * @param other 뺄 MoneyAmount
     * @return 뺀 결과 MoneyAmount
     */
    fun subtract(other: MoneyAmount): MoneyAmount {
        val result = this.amount.subtract(other.amount)
        require(result >= BigDecimal.ZERO) {
            "계산 결과가 음수가 될 수 없습니다. 현재: ${this.amount}, 차감: ${other.amount}"
        }
        return MoneyAmount(result)
    }
    
    /**
     * 곱하기
     * @param multiplier 곱할 값
     * @return 곱한 결과 MoneyAmount
     */
    fun multiply(multiplier: BigDecimal): MoneyAmount {
        return MoneyAmount(this.amount.multiply(multiplier))
    }
    
    /**
     * 나누기
     * @param divisor 나눌 값
     * @return 나눈 결과 MoneyAmount
     */
    fun divide(divisor: BigDecimal): MoneyAmount {
        require(divisor != BigDecimal.ZERO) { "0으로 나눌 수 없습니다." }
        return MoneyAmount(this.amount.divide(divisor, MoneyConstants.DECIMAL_PLACES, RoundingMode.HALF_UP))
    }
    
    /**
     * 다른 MoneyAmount와 비교
     * @param other 비교할 MoneyAmount
     * @return 비교 결과 (-1: 작음, 0: 같음, 1: 큼)
     */
    fun compareTo(other: MoneyAmount): Int {
        return this.amount.compareTo(other.amount)
    }
    
    /**
     * 0인지 확인
     * @return 0인 경우 true, 아닌 경우 false
     */
    fun isZero(): Boolean {
        return this.amount.compareTo(BigDecimal.ZERO) == 0
    }
    
    /**
     * 양수인지 확인
     * @return 양수인 경우 true, 아닌 경우 false
     */
    fun isPositive(): Boolean {
        return this.amount.compareTo(BigDecimal.ZERO) > 0
    }
    
    /**
     * 금액을 문자열로 포맷팅
     * @return 포맷팅된 금액 문자열
     */
    fun format(): String {
        return "${amount.toPlainString()} ${MoneyConstants.CURRENCY_CODE}"
    }
    
    companion object {
        /**
         * 0원 생성
         * @return 0원 MoneyAmount
         */
        fun zero(): MoneyAmount {
            return MoneyAmount(BigDecimal.ZERO)
        }
        
        /**
         * 금액으로 MoneyAmount 생성
         * @param amount 금액
         * @return MoneyAmount
         */
        fun of(amount: BigDecimal): MoneyAmount {
            return MoneyAmount(amount)
        }
        
        /**
         * 금액 문자열로 MoneyAmount 생성
         * @param amount 금액 문자열
         * @return MoneyAmount
         */
        fun of(amount: String): MoneyAmount {
            return MoneyAmount(BigDecimal(amount))
        }
    }
}