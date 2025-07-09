package org.rookedsysc.moneyservice.application.service

import org.rookedsysc.common.UseCase
import org.rookedsysc.moneyservice.application.port.`in`.dto.DecreaseMoneyCommand
import org.rookedsysc.moneyservice.application.port.`in`.usecase.DecreaseMoneyUsecase
import org.rookedsysc.moneyservice.application.port.out.DecreaseMoneyPort
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest
import org.rookedsysc.moneyservice.domain.MoneyChangingStatus
import org.rookedsysc.moneyservice.domain.MoneyChangingType

@UseCase
class DecreaseMoneyService(
    private val decreaseMoneyPort: DecreaseMoneyPort
) : DecreaseMoneyUsecase {
    
    override fun decreaseMoney(command: DecreaseMoneyCommand): MoneyChangingRequest {
        // 1. 머니 변경 요청 생성
        val moneyChangingRequest = decreaseMoneyPort.createMoneyChangingRequest(
            targetMembershipId = command.targetMembershipId,
            amount = command.amount,
            changingType = MoneyChangingType.DECREASE.value
        )
        
        try {
            // 2. 현재 잔액 확인
            val currentMemberMoney = decreaseMoneyPort.getMemberMoney(command.targetMembershipId)
                ?: throw IllegalArgumentException("회원 머니 정보가 존재하지 않습니다.")
            
            // 3. 잔액 부족 확인
            if (!currentMemberMoney.hasEnoughBalance(command.amount)) {
                val failedRequest = moneyChangingRequest.updateStatus(MoneyChangingStatus.FAILED)
                decreaseMoneyPort.updateMoneyChangingRequest(failedRequest)
                throw IllegalArgumentException("잔액이 부족합니다. 현재 잔액: ${currentMemberMoney.balance}")
            }
            
            // 4. 실제 머니 감소 처리
            val updatedMemberMoney = decreaseMoneyPort.decreaseMoney(
                membershipId = command.targetMembershipId,
                amount = command.amount
            )
            
            // 5. 성공 시 요청 상태 업데이트
            val successRequest = moneyChangingRequest.updateStatus(MoneyChangingStatus.SUCCESS)
            return decreaseMoneyPort.updateMoneyChangingRequest(successRequest)
            
        } catch (e: Exception) {
            // 6. 실패 시 요청 상태 업데이트
            val failedRequest = moneyChangingRequest.updateStatus(MoneyChangingStatus.FAILED)
            decreaseMoneyPort.updateMoneyChangingRequest(failedRequest)
            throw e
        }
    }
}