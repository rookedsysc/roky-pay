package org.rookedsysc.membershipservice.application.service

import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaEntity
import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipMapper
import org.rookedsysc.membershipservice.application.port.`in`.dto.RegisterMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.usecase.RegisterMembershipUsecase
import org.rookedsysc.membershipservice.application.port.out.RegisterMembershipPort
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.stereotype.Service

@Service
class RegisterMembershipService(
    private val registerMembershipPort: RegisterMembershipPort,
    private val membershipMapper: MembershipMapper
) : RegisterMembershipUsecase {
    override fun registerMembership(command: RegisterMembershipCommand): MemberShip {
        var membershipJpaEntity: MembershipJpaEntity = membershipMapper.toJpaEntity(command)
        membershipJpaEntity = registerMembershipPort.createMembership(membershipJpaEntity)

        val membership: MemberShip = membershipMapper.toDomainEntity(membershipJpaEntity)
        return membership
    }
}
