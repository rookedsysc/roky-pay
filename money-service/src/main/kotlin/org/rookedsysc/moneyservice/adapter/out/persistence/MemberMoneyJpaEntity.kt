package org.rookedsysc.moneyservice.adapter.out.persistence

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "member_money")
class MemberMoneyJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberMoneyId: Long = 0,
    
    @Column(name = "membership_id", nullable = false)
    val membershipId: String,
    
    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    val balance: BigDecimal
)