package org.roky.pay.money.application.port.`in`

import org.roky.pay.money.domain.MoneyChangingRequest

interface DecreaseMoneyRequestUseCase {
    fun decreaseMoneyRequest(command: MoneyChangingRequestCommand): MoneyChangingRequest
}
