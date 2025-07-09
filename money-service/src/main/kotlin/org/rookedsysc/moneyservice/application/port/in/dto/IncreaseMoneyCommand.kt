package org.rookedsysc.moneyservice.application.port.`in`.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.rookedsysc.common.SelfValidating
import java.math.BigDecimal

class IncreaseMoneyCommand(
    @field:NotBlank
    val targetMembershipId: String,
    
    @field:NotNull
    @field:Positive
    val amount: BigDecimal
) : SelfValidating<IncreaseMoneyCommand>() {
    
    init {
        this.validateSelf()
    }
}