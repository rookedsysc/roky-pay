package org.roky.pay.membership.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "membership")
data class MembershipJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val membershipId: Long? = null,
    val name: String,
    val address: String,
    val email: String,
    val isValid: Boolean,
    val isCorp: Boolean,
) {
    constructor(name: String, address: String, email: String, isValid: Boolean, isCorp: Boolean) :
        this(null, name, address, email, isValid, isCorp)
}
