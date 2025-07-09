package org.rookedsysc.bankingservice.adapter.out.external.bank

data class ExternalFirmbankingRequest(
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int
)