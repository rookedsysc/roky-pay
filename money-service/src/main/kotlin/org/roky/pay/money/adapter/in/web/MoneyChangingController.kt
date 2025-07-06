package org.roky.pay.money.adapter.`in`.web

import org.roky.pay.common.WebAdapter
import org.roky.pay.money.application.port.`in`.IncreaseMoneyRequestUseCase
import org.roky.pay.money.application.port.`in`.MoneyChangingRequestCommand
import org.roky.pay.money.adapter.`in`.web.MoneyChangingRequest
import org.roky.pay.money.adapter.`in`.web.MoneyChangingResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class MoneyChangingController(
    private val increaseMoneyRequestUseCase: IncreaseMoneyRequestUseCase,
) {

    @PostMapping("/money/increase")
    fun increaseMoneyRequest(@RequestBody request: MoneyChangingRequest): MoneyChangingResponse {
        val command = MoneyChangingRequestCommand(
            targetMembershipId = request.targetMembershipId,
            amount = request.amount,
        )
        val moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command)
        return MoneyChangingResponse.fromDomain(moneyChangingRequest)
    }
}
