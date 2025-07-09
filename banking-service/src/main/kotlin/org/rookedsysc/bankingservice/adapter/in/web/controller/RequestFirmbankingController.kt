package org.rookedsysc.bankingservice.adapter.`in`.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.rookedsysc.bankingservice.adapter.`in`.web.dto.RequestFirmbankingRequest
import org.rookedsysc.bankingservice.application.port.`in`.dto.RequestFirmbankingCommand
import org.rookedsysc.bankingservice.application.port.`in`.usecase.RequestFirmbankingUsecase
import org.rookedsysc.bankingservice.common.constant.BankingConstants
import org.rookedsysc.bankingservice.domain.FirmbankingRequest
import org.rookedsysc.common.WebAdapter
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@WebAdapter
@RequestMapping(BankingConstants.BANKING + BankingConstants.FIRMBANKING)
class RequestFirmbankingController(
    private val requestFirmbankingUsecase: RequestFirmbankingUsecase
) {
    
    @Operation(summary = "펌뱅킹 요청")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "펌뱅킹 요청 성공",
                content = [Content(schema = Schema(implementation = FirmbankingRequest::class))]
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
    @PostMapping(BankingConstants.REQUEST)
    fun requestFirmbanking(
        @Valid @RequestBody requestFirmbankingRequest: RequestFirmbankingRequest
    ): FirmbankingRequest {
        val command = RequestFirmbankingCommand(
            fromBankName = requestFirmbankingRequest.fromBankName,
            fromBankAccountNumber = requestFirmbankingRequest.fromBankAccountNumber,
            toBankName = requestFirmbankingRequest.toBankName,
            toBankAccountNumber = requestFirmbankingRequest.toBankAccountNumber,
            moneyAmount = requestFirmbankingRequest.moneyAmount
        )
        
        return requestFirmbankingUsecase.requestFirmbanking(command)
    }
}