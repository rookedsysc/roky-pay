package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.rookedsysc.membershipservice.adapter.out.persistence.MockMembershipJpaEntity.Companion.createMembershipJpaEntityMock
import org.rookedsysc.membershipservice.application.port.out.RegisterMembershipPort
import org.rookedsysc.membershipservice.common.TestObjectMapper.Companion.createObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class FindMembershipControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val registerMembershipPort: RegisterMembershipPort
) {

    private val objectMapper = createObjectMapper()

    @Test
    @DisplayName("값이 정확하게 들어가 있을 경우 회원 조회 성공")
    fun findMembership_WithValidRequest_ShouldFindMembership() {
        // given
        val membershipJpaEntity = createMembershipJpaEntityMock("홍길동")
        val savedMembershipJpaEntity = registerMembershipPort.createMembership(membershipJpaEntity)

        // when
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get("/membership/${savedMembershipJpaEntity.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )

        // then
        mvcResult
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(savedMembershipJpaEntity.name))
            .andExpect(jsonPath("$.email").value(savedMembershipJpaEntity.email))
            .andExpect(jsonPath("$.address").value(savedMembershipJpaEntity.address))
            .andExpect(jsonPath("$.isValid").value(savedMembershipJpaEntity.isValid))
            .andExpect(jsonPath("$.isCorp").value(savedMembershipJpaEntity.isCorp))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    @DisplayName("존재하지 않는 회원 조회시 예외가 발생해야 한다")
    fun findMembership_WithNonExistentId_ShouldThrowException_SimpleVersion() {
        // given // when
        try {
            mockMvc.perform(
                MockMvcRequestBuilders.get("/membership/5")
                    .contentType(MediaType.APPLICATION_JSON)
            )
        } catch (e: Exception) {
            // then
            assertThat(e.message).contains("Membership not found")
        }
    }
}
