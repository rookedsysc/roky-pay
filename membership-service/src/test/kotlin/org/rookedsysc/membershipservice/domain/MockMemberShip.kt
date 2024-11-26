package org.rookedsysc.membershipservice.domain

class MockMemberShip {
    companion object {
        fun createMemberShipMock(name: String): MemberShip {
            return MemberShip(
                id = "1",
                name = name,
                email = "${name}@gmail.com",
                address = "서울시 강남구",
                isValid = true,
                isCorp = false
            )
        }
    }
}
