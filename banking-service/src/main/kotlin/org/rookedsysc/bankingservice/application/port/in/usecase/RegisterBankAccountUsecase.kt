package org.rookedsysc.bankingservice.application.port.`in`.usecase

import org.rookedsysc.bankingservice.application.port.`in`.dto.RegisterBankAccountCommand
import org.rookedsysc.bankingservice.domain.RegisteredBankAccount

interface RegisterBankAccountUsecase {
    fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount?
}