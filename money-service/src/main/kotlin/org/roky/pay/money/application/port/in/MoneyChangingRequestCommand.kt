package org.roky.pay.money.application.port.`in`

import org.roky.pay.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class MoneyChangingRequestCommand(
    @field:NotNull
    val targetMembershipId: String,
    @field:NotNull
    val amount: Int,
) : SelfValidating<MoneyChangingRequestCommand>() {
    init {
        this.validateSelf()
    }
}
