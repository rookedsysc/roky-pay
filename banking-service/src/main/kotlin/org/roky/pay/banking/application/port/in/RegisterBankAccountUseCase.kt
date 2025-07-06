package org.roky.pay.banking.application.port.`in`

import org.roky.pay.banking.domain.RegisteredBankAccount

interface RegisterBankAccountUseCase {
    fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount
}
