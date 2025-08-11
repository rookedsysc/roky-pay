package org.rookedsysc.bankingservice.adapter.`in`.web.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.rookedsysc.bankingservice.adapter.`in`.web.dto.RegisterBankAccountRequest
import org.rookedsysc.bankingservice.application.port.`in`.dto.RegisterBankAccountCommand
import org.rookedsysc.bankingservice.application.port.`in`.usecase.RegisterBankAccountUsecase
import org.rookedsysc.bankingservice.common.constant.BankingConstants
import org.rookedsysc.bankingservice.domain.RegisteredBankAccount
import org.rookedsysc.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@WebAdapter
@RequestMapping(BankingConstants.BANKING + BankingConstants.ACCOUNT)
class RegisterBankAccountController(
    private val registerBankAccountUsecase: RegisterBankAccountUsecase
) {
    
    @Operation(summary = "은행 계좌 등록")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "계좌 등록 성공",
                content = [Content(schema = Schema(implementation = RegisteredBankAccount::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 또는 계좌 검증 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 오류",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PostMapping(BankingConstants.REGISTER)
    fun registerBankAccount(
        @Valid @RequestBody registerBankAccountRequest: RegisterBankAccountRequest
    ): ResponseEntity<RegisteredBankAccount> {
        val command = RegisterBankAccountCommand(
            membershipId = registerBankAccountRequest.membershipId,
            bankName = registerBankAccountRequest.bankName,
            bankAccountNumber = registerBankAccountRequest.bankAccountNumber,
            isValid = registerBankAccountRequest.isValid
        )
        
        val result = registerBankAccountUsecase.registerBankAccount(command)
        
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}