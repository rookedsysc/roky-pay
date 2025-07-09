package org.rookedsysc.moneyservice.application.service

import org.rookedsysc.common.UseCase
import org.rookedsysc.moneyservice.application.port.`in`.dto.IncreaseMoneyCommand
import org.rookedsysc.moneyservice.application.port.`in`.usecase.IncreaseMoneyUsecase
import org.rookedsysc.moneyservice.application.port.out.IncreaseMoneyPort
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest
import org.rookedsysc.moneyservice.domain.MoneyChangingStatus
import org.rookedsysc.moneyservice.domain.MoneyChangingType

@UseCase
class IncreaseMoneyService(
    private val increaseMoneyPort: IncreaseMoneyPort
) : IncreaseMoneyUsecase {
    
    override fun increaseMoney(command: IncreaseMoneyCommand): MoneyChangingRequest {
        // 1. 머니 변경 요청 생성
        val moneyChangingRequest = increaseMoneyPort.createMoneyChangingRequest(
            targetMembershipId = command.targetMembershipId,
            amount = command.amount,
            changingType = MoneyChangingType.INCREASE.value
        )
        
        try {
            // 2. 실제 머니 증가 처리
            val updatedMemberMoney = increaseMoneyPort.increaseMoney(
                membershipId = command.targetMembershipId,
                amount = command.amount
            )
            
            // 3. 성공 시 요청 상태 업데이트
            val successRequest = moneyChangingRequest.updateStatus(MoneyChangingStatus.SUCCESS)
            return increaseMoneyPort.updateMoneyChangingRequest(successRequest)
            
        } catch (e: Exception) {
            // 4. 실패 시 요청 상태 업데이트
            val failedRequest = moneyChangingRequest.updateStatus(MoneyChangingStatus.FAILED)
            increaseMoneyPort.updateMoneyChangingRequest(failedRequest)
            throw e
        }
    }
}