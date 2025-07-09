package org.rookedsysc.moneyservice.adapter.out.persistence

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "money_changing_request")
class MoneyChangingRequestJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val moneyChangingRequestId: Long = 0,
    
    @Column(name = "target_membership_id", nullable = false)
    val targetMembershipId: String,
    
    @Column(name = "money_changing_type", nullable = false)
    val moneyChangingType: Int,
    
    @Column(name = "money_amount", nullable = false, precision = 19, scale = 2)
    val moneyAmount: BigDecimal,
    
    @Column(name = "changing_money_status", nullable = false)
    val changingMoneyStatus: Int,
    
    @Column(name = "uuid", nullable = false)
    val uuid: String,
    
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime
)