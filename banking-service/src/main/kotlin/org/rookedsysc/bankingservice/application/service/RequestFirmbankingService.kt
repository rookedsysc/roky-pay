package org.rookedsysc.bankingservice.application.service

import org.rookedsysc.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest
import org.rookedsysc.bankingservice.application.port.`in`.dto.RequestFirmbankingCommand
import org.rookedsysc.bankingservice.application.port.`in`.usecase.RequestFirmbankingUsecase
import org.rookedsysc.bankingservice.application.port.out.RequestExternalFirmbankingPort
import org.rookedsysc.bankingservice.application.port.out.RequestFirmbankingPort
import org.rookedsysc.bankingservice.domain.FirmbankingRequest
import org.rookedsysc.bankingservice.domain.FirmbankingStatus
import org.rookedsysc.common.UseCase
import java.util.*

@UseCase
class RequestFirmbankingService(
    private val requestFirmbankingPort: RequestFirmbankingPort,
    private val requestExternalFirmbankingPort: RequestExternalFirmbankingPort
) : RequestFirmbankingUsecase {
    
    override fun requestFirmbanking(command: RequestFirmbankingCommand): FirmbankingRequest {
        // 1. 펌뱅킹 요청 생성 (초기 상태: REQUESTED)
        val firmbankingRequest = FirmbankingRequest(
            firmbankingRequestId = "0", // Auto-generated
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            moneyAmount = command.moneyAmount,
            firmbankingStatus = FirmbankingStatus.REQUESTED,
            uuid = UUID.randomUUID().toString()
        )
        
        // 2. 펌뱅킹 요청 저장
        val savedRequest = requestFirmbankingPort.createFirmbankingRequest(firmbankingRequest)
        
        // 3. 외부 은행 시스템에 펌뱅킹 요청
        val externalRequest = ExternalFirmbankingRequest(
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            moneyAmount = command.moneyAmount
        )
        
        return try {
            val result = requestExternalFirmbankingPort.requestExternalFirmbanking(externalRequest)
            
            // 4. 외부 시스템 응답에 따라 상태 업데이트
            val updatedStatus = if (result.isSuccess()) {
                FirmbankingStatus.COMPLETED
            } else {
                FirmbankingStatus.FAILED
            }
            
            val updatedRequest = savedRequest.updateStatus(updatedStatus)
            requestFirmbankingPort.updateFirmbankingRequest(updatedRequest)
            
        } catch (e: Exception) {
            // 5. 외부 시스템 오류 시 실패 상태로 업데이트
            val failedRequest = savedRequest.updateStatus(FirmbankingStatus.FAILED)
            requestFirmbankingPort.updateFirmbankingRequest(failedRequest)
        }
    }
}