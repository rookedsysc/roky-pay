package org.roky.pay.banking.adapter.`in`.web

data class RegisterBankAccountRequest(
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean,
)
