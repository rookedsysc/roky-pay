package org.rookedsysc.moneyservice.application.port.`in`.usecase

import org.rookedsysc.moneyservice.application.port.`in`.dto.DecreaseMoneyCommand
import org.rookedsysc.moneyservice.domain.MoneyChangingRequest

interface DecreaseMoneyUsecase {
    fun decreaseMoney(command: DecreaseMoneyCommand): MoneyChangingRequest
}