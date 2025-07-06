package org.roky.pay.membership.application.port.`in`

data class ModifyMembershipCommand(
    val membershipId: String,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean,
)
