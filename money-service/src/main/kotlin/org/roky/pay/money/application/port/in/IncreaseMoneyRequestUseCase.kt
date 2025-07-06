package org.roky.pay.money.application.port.`in`

import org.roky.pay.money.domain.MoneyChangingRequest

interface IncreaseMoneyRequestUseCase {
    fun increaseMoneyRequest(command: MoneyChangingRequestCommand): MoneyChangingRequest
}
