package org.rookedsysc.bankingservice.application.service

import org.rookedsysc.bankingservice.adapter.out.external.bank.GetBankAccountRequest
import org.rookedsysc.bankingservice.application.port.`in`.dto.RegisterBankAccountCommand
import org.rookedsysc.bankingservice.application.port.`in`.usecase.RegisterBankAccountUsecase
import org.rookedsysc.bankingservice.application.port.out.RegisterBankAccountPort
import org.rookedsysc.bankingservice.application.port.out.RequestBankAccountInfoPort
import org.rookedsysc.bankingservice.domain.RegisteredBankAccount
import org.rookedsysc.common.UseCase

@UseCase
class RegisterBankAccountService(
    private val registerBankAccountPort: RegisterBankAccountPort,
    private val requestBankAccountInfoPort: RequestBankAccountInfoPort
) : RegisterBankAccountUsecase {
    
    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount? {
        // 1. 외부 은행 시스템에서 계좌 정보 검증
        val getBankAccountRequest = GetBankAccountRequest(
            bankName = command.bankName,
            bankAccountNumber = command.bankAccountNumber
        )
        
        return try {
            val bankAccount = requestBankAccountInfoPort.getBankAccountInfo(getBankAccountRequest)
            
            // 2. 계좌가 유효한 경우에만 등록
            if (bankAccount.isValid) {
                val registeredBankAccount = RegisteredBankAccount(
                    registeredBankAccountId = "0", // Auto-generated
                    membershipId = command.membershipId,
                    bankName = command.bankName,
                    bankAccountNumber = command.bankAccountNumber,
                    linkedStatusIsValid = command.isValid
                )
                
                registerBankAccountPort.createRegisteredBankAccount(registeredBankAccount)
            } else {
                null
            }
        } catch (e: Exception) {
            // 3. 외부 은행 시스템 오류 시 null 반환
            null
        }
    }
}