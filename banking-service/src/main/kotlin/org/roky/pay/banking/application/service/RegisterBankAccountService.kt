package org.roky.pay.banking.application.service

import org.roky.pay.common.UseCase
import org.roky.pay.banking.application.port.`in`.RegisterBankAccountCommand
import org.roky.pay.banking.application.port.`in`.RegisterBankAccountUseCase
import org.roky.pay.banking.application.port.out.GetExternalBankAccountInfoPort
import org.roky.pay.banking.application.port.out.RegisterBankAccountPort
import org.roky.pay.banking.domain.BankAccount
import org.roky.pay.banking.domain.BankAccountId
import org.roky.pay.banking.domain.RegisteredBankAccount
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RegisterBankAccountService(
    private val registerBankAccountPort: RegisterBankAccountPort,
    private val getExternalBankAccountInfoPort: GetExternalBankAccountInfoPort,
) : RegisterBankAccountUseCase {
    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount {

        // 1. call external system1 (membership-service)
        // to check if member is valid.
        val resp = getExternalBankAccountInfoPort.getExternalBankAccountInfo(
            BankAccount.generateBankAccount(
                BankAccountId("123")
            )
        )

        // 2. call external system2 (firm-banking system)
        // to check if the BankAccount is valid


        // 3. if ok,
        // Register Bank Account
        val registeredBankAccount = registerBankAccountPort.createRegisteredBankAccount(
            membershipId = command.membershipId,
            bankName = command.bankName,
            bankAccountNumber = command.bankAccountNumber,
            linkedStatusIsValid = command.linkedStatusIsValid,
        )

        // 4. write result to DB
        // Write History of Registered Bank Account


        // entity -> Membership
        return registeredBankAccount
    }
}
