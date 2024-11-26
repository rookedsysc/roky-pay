package org.rookedsysc.membershipservice.application.port.`in`.dto

class MockRegisterMembershipCommand {
    companion object {
        fun createRegisterMembershipCommandMock(name: String): RegisterMembershipCommand {
            return RegisterMembershipCommand(
                name = name,
                email = "${name}@gamil.com",
                address = "서울시 강남구",
                isValid = true,
                isCorp = false
            )
        }
    }
}
