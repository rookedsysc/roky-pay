package org.rookedsysc.bankingservice.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "registered_bank_account")
class RegisteredBankAccountJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val registeredBankAccountId: Long = 0,
    
    @Column(name = "membership_id", nullable = false)
    val membershipId: String,
    
    @Column(name = "bank_name", nullable = false)
    val bankName: String,
    
    @Column(name = "bank_account_number", nullable = false)
    val bankAccountNumber: String,
    
    @Column(name = "linked_status_is_valid", nullable = false)
    val linkedStatusIsValid: Boolean
)