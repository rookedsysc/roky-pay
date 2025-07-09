package org.rookedsysc.moneyservice.domain

/**
 * 머니 변경 상태
 * 머니 변경 요청의 처리 상태를 나타내는 열거형
 */
enum class MoneyChangingStatus {
    /**
     * 처리 대기
     */
    PENDING,
    
    /**
     * 처리 성공
     */
    SUCCESS,
    
    /**
     * 처리 실패
     */
    FAILED
}