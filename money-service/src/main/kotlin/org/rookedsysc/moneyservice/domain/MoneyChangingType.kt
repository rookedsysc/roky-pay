package org.rookedsysc.moneyservice.domain

/**
 * 머니 변경 타입
 * 머니 충전/사용을 구분하는 열거형
 */
enum class MoneyChangingType {
    /**
     * 충전
     */
    CHARGE,
    
    /**
     * 사용
     */
    USE
}