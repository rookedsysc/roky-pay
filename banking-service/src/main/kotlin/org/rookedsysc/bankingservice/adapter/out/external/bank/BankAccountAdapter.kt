package org.rookedsysc.bankingservice.adapter.out.external.bank

import org.rookedsysc.bankingservice.application.port.out.RequestBankAccountInfoPort
import org.rookedsysc.bankingservice.application.port.out.RequestExternalFirmbankingPort
import org.rookedsysc.common.ExternalSystemAdapter

@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort, RequestExternalFirmbankingPort {
    
    override fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount {
        // 실제 외부 은행 시스템 연동 로직
        // 현재는 모의 구현으로 계좌번호가 "1234567890"이면 유효한 계좌로 처리
        val isValid = when {
            request.bankAccountNumber == "1234567890" -> true
            request.bankAccountNumber.length >= 10 -> true
            else -> false
        }
        
        return BankAccount(
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            isValid = isValid
        )
    }
    
    override fun requestExternalFirmbanking(request: ExternalFirmbankingRequest): FirmbankingResult {
        // 실제 외부 은행 시스템 펌뱅킹 요청 로직
        // 현재는 모의 구현으로 다음 조건에서 성공으로 처리:
        // 1. 송금 금액이 양수
        // 2. 계좌 번호가 유효한 형식
        // 3. 은행 이름이 비어있지 않음
        
        val isSuccess = when {
            request.moneyAmount <= 0 -> false
            request.fromBankAccountNumber.length < 10 -> false
            request.toBankAccountNumber.length < 10 -> false
            request.fromBankName.isEmpty() -> false
            request.toBankName.isEmpty() -> false
            else -> true
        }
        
        return FirmbankingResult(
            resultCode = if (isSuccess) 0 else 1
        )
    }
}