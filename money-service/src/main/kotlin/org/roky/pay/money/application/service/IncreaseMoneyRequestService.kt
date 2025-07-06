package org.roky.pay.money.application.service

import org.roky.pay.common.UseCase
import org.roky.pay.money.application.port.`in`.IncreaseMoneyRequestUseCase
import org.roky.pay.money.application.port.`in`.MoneyChangingRequestCommand
import org.roky.pay.money.application.port.out.GetBankingPort
import org.roky.pay.money.application.port.out.GetMembershipPort
import org.roky.pay.money.application.port.out.MoneyChangingRequestPort
import org.roky.pay.money.domain.MoneyChangingRequest
import org.roky.pay.money.domain.MoneyChangingStatus
import org.roky.pay.money.domain.MoneyChangingType
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@UseCase
@Transactional
class IncreaseMoneyRequestService(
    private val moneyChangingRequestPort: MoneyChangingRequestPort,
    private val getMembershipPort: GetMembershipPort,
    private val getBankingPort: GetBankingPort,
) : IncreaseMoneyRequestUseCase {
    override fun increaseMoneyRequest(command: MoneyChangingRequestCommand): MoneyChangingRequest {
        // 1. 고객 정보가 정상인지 확인 (멤버십 서비스)
        val membershipStatus = getMembershipPort.getMembershipStatus(command.targetMembershipId)
        if (!membershipStatus) {
            throw RuntimeException("Invalid membership")
        }

        // 2. 고객의 연동된 계좌가 있는지 확인 (뱅킹 서비스)
        val bankAccountStatus = getBankingPort.getBankAccountStatus(command.targetMembershipId)
        if (!bankAccountStatus) {
            throw RuntimeException("Invalid bank account")
        }

        // 3. 증액 요청 기록
        val moneyChangingRequest = moneyChangingRequestPort.createMoneyChangingRequest(
            targetMembershipId = command.targetMembershipId,
            moneyChangingType = MoneyChangingType.INCREASE,
            moneyChangingAmount = command.amount,
            moneyChangingStatus = MoneyChangingStatus.REQUESTED,
            uuid = UUID.randomUUID().toString(),
        )

        // 4. 펌뱅킹을 통해 법인 계좌 -> 고객 개인 계좌 입금 요청 (뱅킹 서비스)
        // TODO: Implement actual firmbanking logic

        // 5. 결과에 따라 상태 변경
        val resultStatus = MoneyChangingStatus.SUCCEEDED // Assume success for now
        return moneyChangingRequestPort.modifyMoneyChangingRequest(
            moneyChangingRequestId = moneyChangingRequest.moneyChangingRequestId.value,
            moneyChangingStatus = resultStatus,
        )
    }
}
