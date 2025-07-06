package org.roky.pay.banking.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "registered_bank_account")
data class RegisteredBankAccountJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean,
) {
    constructor(membershipId: String, bankName: String, bankAccountNumber: String, linkedStatusIsValid: Boolean) :
        this(null, membershipId, bankName, bankAccountNumber, linkedStatusIsValid)
}
