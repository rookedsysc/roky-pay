package org.roky.pay.membership.adapter.out.persistence

import org.roky.pay.common.PersistenceAdapter
import org.roky.pay.membership.application.port.out.FindMembershipPort
import org.roky.pay.membership.application.port.out.ModifyMembershipPort
import org.roky.pay.membership.application.port.out.RegisterMembershipPort
import org.roky.pay.membership.domain.Membership
import org.roky.pay.membership.domain.MembershipAddress
import org.roky.pay.membership.domain.MembershipEmail
import org.roky.pay.membership.domain.MembershipId
import org.roky.pay.membership.domain.MembershipIsCorp
import org.roky.pay.membership.domain.MembershipIsValid
import org.roky.pay.membership.domain.MembershipName


@PersistenceAdapter
class MembershipPersistenceAdapter(
    private val membershipRepository: SpringDataMembershipRepository,
) : RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    override fun createMembership(
        name: MembershipName,
        email: MembershipEmail,
        address: MembershipAddress,
        isValid: MembershipIsValid,
        isCorp: MembershipIsCorp,
    ): Membership {
        val entity = MembershipJpaEntity(
            name = name.value,
            address = address.value,
            email = email.value,
            isValid = isValid.value,
            isCorp = isCorp.value,
        )
        val savedEntity = membershipRepository.save(entity)
        return mapToDomainEntity(savedEntity)
    }

    override fun findMembership(membershipId: MembershipId): Membership? {
        val entity = membershipRepository.findById(membershipId.value.toLong()).orElse(null)
        return entity?.let { mapToDomainEntity(it) }
    }

    override fun modifyMembership(
        membershipId: MembershipId,
        name: MembershipName,
        email: MembershipEmail,
        address: MembershipAddress,
        isValid: MembershipIsValid,
        isCorp: MembershipIsCorp,
    ): Membership {
        val entity = membershipRepository.findById(membershipId.value.toLong())
            .orElseThrow { NoSuchElementException("Membership not found") }

        val updatedEntity = entity.copy(
            name = name.value,
            address = address.value,
            email = email.value,
            isValid = isValid.value,
            isCorp = isCorp.value,
        )
        val savedEntity = membershipRepository.save(updatedEntity)
        return mapToDomainEntity(savedEntity)
    }

    private fun mapToDomainEntity(entity: MembershipJpaEntity): Membership {
        return Membership.create(
            membershipId = MembershipId(entity.membershipId.toString()),
            name = MembershipName(entity.name),
            email = MembershipEmail(entity.email),
            address = MembershipAddress(entity.address),
            isValid = MembershipIsValid(entity.isValid),
            isCorp = MembershipIsCorp(entity.isCorp),
        )
    }
}
