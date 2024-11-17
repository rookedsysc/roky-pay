package org.rookedsysc.membershipservice.domain

class MemberShip(
    private val id: String,
    private val name: String,
    private val email: String,
    private val address: String,
    private val isValid: Boolean,
    private val isCorp: Boolean
) {}
