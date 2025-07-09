package org.rookedsysc.moneyservice.adapter.`in`.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.rookedsysc.common.WebAdapter
import org.rookedsysc.moneyservice.adapter.`in`.web.dto.DecreaseMoneyRequest
import org.rookedsysc.moneyservice.adapter.`in`.web.dto.IncreaseMoneyRequest
import org.rookedsysc.moneyservice.adapter.`in`.web.dto.MoneyChangingResult
import org.rookedsysc.moneyservice.application.port.`in`.dto.DecreaseMoneyCommand
import org.rookedsysc.moneyservice.application.port.`in`.dto.IncreaseMoneyCommand
import org.rookedsysc.moneyservice.application.port.`in`.usecase.DecreaseMoneyUsecase
import org.rookedsysc.moneyservice.application.port.`in`.usecase.IncreaseMoneyUsecase
import org.rookedsysc.moneyservice.common.constant.MoneyConstants
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@WebAdapter
@RequestMapping(MoneyConstants.MONEY)
class MoneyController(
    private val increaseMoneyUsecase: IncreaseMoneyUsecase,
    private val decreaseMoneyUsecase: DecreaseMoneyUsecase
) {
    
    @Operation(summary = "머니 충전")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "머니 충전 성공",
                content = [Content(schema = Schema(implementation = MoneyChangingResult::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 오류",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PostMapping(MoneyConstants.INCREASE_MONEY)
    fun increaseMoney(
        @Valid @RequestBody increaseMoneyRequest: IncreaseMoneyRequest
    ): MoneyChangingResult {
        val command = IncreaseMoneyCommand(
            targetMembershipId = increaseMoneyRequest.targetMembershipId,
            amount = increaseMoneyRequest.amount
        )
        
        val result = increaseMoneyUsecase.increaseMoney(command)
        return MoneyChangingResult.from(result)
    }
    
    @Operation(summary = "머니 사용")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "머니 사용 성공",
                content = [Content(schema = Schema(implementation = MoneyChangingResult::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 또는 잔액 부족",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 오류",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PostMapping(MoneyConstants.DECREASE_MONEY)
    fun decreaseMoney(
        @Valid @RequestBody decreaseMoneyRequest: DecreaseMoneyRequest
    ): MoneyChangingResult {
        val command = DecreaseMoneyCommand(
            targetMembershipId = decreaseMoneyRequest.targetMembershipId,
            amount = decreaseMoneyRequest.amount
        )
        
        val result = decreaseMoneyUsecase.decreaseMoney(command)
        return MoneyChangingResult.from(result)
    }
}