package org.rookedsysc.membershipservice.adapter.out.persistence

class MockMembershipJpaEntity {
    companion object {
        fun createMembershipJpaEntityMock(name: String): MembershipJpaEntity {
            return MembershipJpaEntity(
                id = 1,
                name = name,
                email = "${name}@gmail.com",
                address = "서울시 강남구",
                isValid = true,
                isCorp = false
            )
        }
    }
}
