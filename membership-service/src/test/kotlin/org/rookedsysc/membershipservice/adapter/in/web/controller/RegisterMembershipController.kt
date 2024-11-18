package org.rookedsysc.membershipservice.adapter.`in`.web.controller

import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.rookedsysc.membershipservice.adapter.`in`.web.dto.MockRegisterMembershipRequest.Companion.createMockRegisterMembershipRequest
import org.rookedsysc.membershipservice.application.port.out.FindMembershipPort
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
import kotlin.test.Test

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class RegisterMembershipControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val findMembershipPort: FindMembershipPort,
) {
    private val objectMapper = createObjectMapper()

    @Test
    @DisplayName("올바른 요청으로 회원가입시 DB에 성공적으로 회원이 저장되어야 한다")
    fun registerMembership_WithValidRequest_ShouldCreateMembershipInDB() {
        // given
        val validRequest = createMockRegisterMembershipRequest("홍길동")

        // when
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(validRequest.name))
            .andExpect(jsonPath("$.email").value(validRequest.email))
            .andExpect(jsonPath("$.address").value(validRequest.address))
            .andExpect(jsonPath("$.isValid").value(validRequest.isValid))
            .andExpect(jsonPath("$.isCorp").value(validRequest.isCorp))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()

        // then
        // ID 추출
        val responseBody = mvcResult.response.contentAsString
        val responseJson = objectMapper.readTree(responseBody)
        val savedId = responseJson.get("id").asLong()

        // DB 검증
        val savedMembership = findMembershipPort.getMembership(savedId)
        assertNotNull(savedMembership, "저장된 회원이 존재해야 합니다")
        with(savedMembership!!) {
            assertEquals(validRequest.name, name)
            assertEquals(validRequest.email, email)
            assertEquals(validRequest.address, address)
            assertEquals(validRequest.isValid, isValid)
            assertEquals(validRequest.isCorp, isCorp)
        }
    }
}
