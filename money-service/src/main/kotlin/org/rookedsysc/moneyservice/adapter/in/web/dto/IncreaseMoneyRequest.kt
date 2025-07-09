package org.rookedsysc.moneyservice.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class IncreaseMoneyRequest(
    @field:NotBlank
    val targetMembershipId: String,
    
    @field:NotNull
    @field:Positive
    val amount: BigDecimal
)