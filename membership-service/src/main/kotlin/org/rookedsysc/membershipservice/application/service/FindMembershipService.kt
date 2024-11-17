package org.rookedsysc.membershipservice.application.service

import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipJpaEntity
import org.rookedsysc.membershipservice.adapter.out.persistence.MembershipMapper
import org.rookedsysc.membershipservice.application.port.`in`.usecase.FindMembershipUsecase
import org.rookedsysc.membershipservice.application.port.out.FindMembershipPort
import org.rookedsysc.membershipservice.domain.MemberShip
import org.springframework.stereotype.Service

@Service
class FindMembershipService(
    private val findMembershipPort: FindMembershipPort,
    private val membershipMapper: MembershipMapper
) : FindMembershipUsecase{
    override fun getMembership(id: Long): MemberShip{
        val membershipJpaRepository: MembershipJpaEntity = findMembershipPort.getMembership(id)
        val membership: MemberShip = membershipMapper.toDomainEntity(membershipJpaRepository)
         return membership
    }
}
