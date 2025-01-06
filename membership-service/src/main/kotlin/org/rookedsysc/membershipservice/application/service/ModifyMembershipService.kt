package org.rookedsysc.membershipservice.application.service

import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaEntity
import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipMapper
import org.rookedsysc.membershipservice.application.port.`in`.dto.ModifyMembershipCommand
import org.rookedsysc.membershipservice.application.port.`in`.usecase.ModifyMembershipUsecase
import org.rookedsysc.membershipservice.application.port.out.FindMembershipPort
import org.rookedsysc.membershipservice.application.port.out.ModifyMembershipPort
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.stereotype.Service

@Service
class ModifyMembershipService(
    private val findMembershipPort: FindMembershipPort,
    private val modifyMembershipPort: ModifyMembershipPort,
    private val membershipMapper: MembershipMapper
) : ModifyMembershipUsecase {
    override fun modifyMembership(modifyMembershipCommand: ModifyMembershipCommand): MemberShip {
        val jpaEntity: MembershipJpaEntity = findMembershipPort.getMembership(modifyMembershipCommand.id)
        val beforeChange: MemberShip = membershipMapper.toDomainEntity(jpaEntity)
        val modifiedMembership: MemberShip = beforeChange.modify(modifyMembershipCommand)

        val savedChange: MembershipJpaEntity =
            modifyMembershipPort.modifyMembership(membershipMapper.toJpaEntity(modifiedMembership))

        return membershipMapper.toDomainEntity(savedChange)
    }
}
