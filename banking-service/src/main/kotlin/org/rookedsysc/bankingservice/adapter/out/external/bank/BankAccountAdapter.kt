package org.rookedsysc.bankingservice.adapter.out.external.bank

import org.rookedsysc.bankingservice.application.port.out.RequestBankAccountInfoPort
import org.rookedsysc.common.ExternalSystemAdapter

@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort {
    
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
}