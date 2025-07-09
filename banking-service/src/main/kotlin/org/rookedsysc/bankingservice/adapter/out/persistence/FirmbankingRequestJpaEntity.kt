package org.rookedsysc.bankingservice.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "firmbanking_request")
class FirmbankingRequestJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val firmbankingRequestId: Long = 0,
    
    @Column(name = "from_bank_name", nullable = false)
    val fromBankName: String,
    
    @Column(name = "from_bank_account_number", nullable = false)
    val fromBankAccountNumber: String,
    
    @Column(name = "to_bank_name", nullable = false)
    val toBankName: String,
    
    @Column(name = "to_bank_account_number", nullable = false)
    val toBankAccountNumber: String,
    
    @Column(name = "money_amount", nullable = false)
    val moneyAmount: Int,
    
    @Column(name = "firmbanking_status", nullable = false)
    val firmbankingStatus: Int,
    
    @Column(name = "uuid", nullable = false)
    val uuid: String
)