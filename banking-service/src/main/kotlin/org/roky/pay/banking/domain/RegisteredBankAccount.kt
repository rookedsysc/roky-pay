package org.roky.pay.banking.domain

data class RegisteredBankAccount(
    val registeredBankAccountId: RegisteredBankAccountId,
) {
    companion object {
        fun generateRegisteredBankAccount(
            registeredBankAccountId: RegisteredBankAccountId,
        ) = RegisteredBankAccount(
            registeredBankAccountId = registeredBankAccountId,
        )
    }
}

@JvmInline
value class RegisteredBankAccountId(val value: String)
