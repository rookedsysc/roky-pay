package org.rookedsysc.moneyservice.application.port.`in`.usecase

import org.rookedsysc.moneyservice.application.port.`in`.dto.IncreaseMoneyCommand
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest

interface IncreaseMoneyUsecase {
    fun increaseMoney(command: IncreaseMoneyCommand): MoneyChangingRequest
}