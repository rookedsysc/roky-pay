package org.roky.pay.banking.adapter.`in`.web

import org.roky.pay.common.WebAdapter
import org.roky.pay.banking.application.port.`in`.RegisterBankAccountCommand
import org.roky.pay.banking.application.port.`in`.RegisterBankAccountUseCase
import org.roky.pay.banking.domain.RegisteredBankAccount
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterBankAccountController(
    private val registerBankAccountUseCase: RegisterBankAccountUseCase,
) {

    @PostMapping("/banking/register/account")
    fun registerBankAccount(@RequestBody request: RegisterBankAccountRequest): RegisteredBankAccount {
        val command = RegisterBankAccountCommand(
            membershipId = request.membershipId,
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            linkedStatusIsValid = request.linkedStatusIsValid,
        )
        return registerBankAccountUseCase.registerBankAccount(command)
    }
}
