package org.rookedsysc.membershipservice.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MembershipJpaRepository : JpaRepository<MembershipJpaEntity, Long> {
}
