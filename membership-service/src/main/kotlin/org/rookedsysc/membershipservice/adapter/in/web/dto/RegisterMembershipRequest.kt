package org.rookedsysc.membershipservice.adapter.`in`.web.dto

data class RegisterMembershipRequest(
    val name: String,
    val address: String,
    val email: String,
    val isValid: Boolean,
    val isCorp: Boolean
) {}
