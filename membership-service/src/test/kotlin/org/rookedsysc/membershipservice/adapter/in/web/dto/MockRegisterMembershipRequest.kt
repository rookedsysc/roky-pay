package org.rookedsysc.membershipservice.adapter.`in`.web.dto

import org.junit.jupiter.api.Assertions.*

class MockRegisterMembershipRequest {
    companion object {
        fun createMockRegisterMembershipRequest(name: String): RegisterMembershipRequest {
            return RegisterMembershipRequest(
                name = name,
                email = "$name@gmail.com",
                address = "서울 강동구",
                isValid = true,
                isCorp = false
            )
        }
    }
}
