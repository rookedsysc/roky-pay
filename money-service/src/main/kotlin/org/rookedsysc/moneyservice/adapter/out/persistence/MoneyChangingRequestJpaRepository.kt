package org.rookedsysc.moneyservice.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MoneyChangingRequestJpaRepository : JpaRepository<MoneyChangingRequestJpaEntity, Long>