package org.rookedsysc.moneyservice.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberMoneyJpaRepository : JpaRepository<MemberMoneyJpaEntity, Long> {
    
    @Query("SELECT m FROM MemberMoneyJpaEntity m WHERE m.membershipId = :membershipId")
    fun findByMembershipId(membershipId: String): MemberMoneyJpaEntity?
}