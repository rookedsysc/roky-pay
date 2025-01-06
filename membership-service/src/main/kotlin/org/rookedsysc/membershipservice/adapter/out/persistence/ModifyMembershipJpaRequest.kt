package org.rookedsysc.membershipservice.adapter.out.persistence

data class ModifyMembershipJpaRequest(
    val id: Long,

    val name: String?,

    val address: String?,

    val email: String?,

    val isCorp: Boolean?
) {
}
