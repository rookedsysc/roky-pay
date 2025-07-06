package org.roky.pay.banking.domain

data class BankAccount(
    val bankAccountId: BankAccountId,
) {
    companion object {
        fun generateBankAccount(
            bankAccountId: BankAccountId,
        ) = BankAccount(
            bankAccountId = bankAccountId,
        )
    }
}

@JvmInline
value class BankAccountId(val value: String)
