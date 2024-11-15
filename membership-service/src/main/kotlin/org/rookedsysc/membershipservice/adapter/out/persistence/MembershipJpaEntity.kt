package org.rookedsysc.membershipservice.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "membership")
class MembershipJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var email: String,

    var address: String,

    @Column(name = "is_valid")
    var isValid: Boolean,

    @Column(name = "is_corp")
    var isCorp: Boolean
) {}
