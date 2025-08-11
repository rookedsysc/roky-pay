package org.rookedsysc.bankingservice.adapter.out.external.bank

data class GetBankAccountRequest(
    val bankName: String,
    val bankAccountNumber: String
)