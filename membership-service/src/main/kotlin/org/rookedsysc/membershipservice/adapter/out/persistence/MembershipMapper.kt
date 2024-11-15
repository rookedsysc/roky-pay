package org.rookedsysc.membershipservice.adapter.out.persistence

import org.rookedsysc.membershipservice.application.port.`in`.dto.RegisterMembershipCommand
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.stereotype.Component

@Component
class MembershipMapper {
    fun toJpaEntity(membershipCommand: RegisterMembershipCommand): MembershipJpaEntity {
        return MembershipJpaEntity(
            name = membershipCommand.name,
            email = membershipCommand.email,
            address = membershipCommand.address,
            isValid = membershipCommand.isValid,
            isCorp = membershipCommand.isCorp
        )
    }

    fun toDomainEntity(membershipJpaEntity: MembershipJpaEntity): MemberShip {
        return MemberShip(
            id = membershipJpaEntity.id.toString(),
            name = membershipJpaEntity.name,
            email = membershipJpaEntity.email,
            address = membershipJpaEntity.address,
            isValid = membershipJpaEntity.isValid,
            isCorp = membershipJpaEntity.isCorp
        )
    }
}
