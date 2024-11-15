package org.rookedsysc.membershipservice.domain

class MemberShip(
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean
) {}
