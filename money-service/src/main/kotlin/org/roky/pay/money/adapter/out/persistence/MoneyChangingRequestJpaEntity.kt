package org.roky.pay.money.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "money_changing_request")
data class MoneyChangingRequestJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val moneyChangingRequestId: Long? = null,
    val targetMembershipId: String,
    val moneyChangingType: Int,
    val moneyChangingAmount: Int,
    val moneyChangingStatus: Int,
    val uuid: String,
    val createdAt: LocalDateTime,
) {
    constructor(
        targetMembershipId: String,
        moneyChangingType: Int,
        moneyChangingAmount: Int,
        moneyChangingStatus: Int,
        uuid: String,
        createdAt: LocalDateTime,
    ) : this(null, targetMembershipId, moneyChangingType, moneyChangingAmount, moneyChangingStatus, uuid, createdAt)
}
