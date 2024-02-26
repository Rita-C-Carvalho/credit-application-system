package me.dio.credit.application.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.dio.credit.application.system.dto.CustomerDto
import me.dio.credit.application.system.dto.CustomerUpdateDto
import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerControllerTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/customers"
    }

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @AfterEach
    fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`() {
        //given
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect { MockMvcResultMatchers.status().isCreated }
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a customer with same CPF and return 409 status`() {
        //given
        customerRepository.save(buildCustomerDto().toEntity())
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect { MockMvcResultMatchers.status().isConflict }
            .andExpect { MockMvcResultMatchers.jsonPath("$.timestamp").exists() }
            .andExpect { MockMvcResultMatchers.jsonPath("$.status").value(409) }
            .andExpect {
                MockMvcResultMatchers.jsonPath("$.exception").value(
                    "class org.springframework.dao.DataIntegrityViolationException"
                )
            }
            .andExpect { MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty }
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a customer with firstName empty and return 400 status`() {
        //given
        val customerDto: CustomerDto = buildCustomerDto(firstName = "")
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect { MockMvcResultMatchers.status().isBadRequest }
            .andExpect { MockMvcResultMatchers.jsonPath("$.timestamp").exists() }
            .andExpect { MockMvcResultMatchers.jsonPath("$.status").value(400) }
            .andExpect {
                MockMvcResultMatchers.jsonPath("$.exception").value(
                    "class org.springframework.web.bind.MethodArgumentNotValidException"
                )
            }
            .andExpect { MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty }
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find customer by id and retunr 200 status`() {
        //given
        val customer: Customer = customerRepository.save(buildCustomerDto().toEntity())

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect { MockMvcResultMatchers.status().isOk }
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val customer: Customer = customerRepository.save(buildCustomerDto().toEntity())

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect { MockMvcResultMatchers.status().isNoContent }
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not delete customer by id`() {
        //given
        val invalidId: Long? = null

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${invalidId}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect { MockMvcResultMatchers.status().isBadRequest }
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should update a customer and return 200 status`() {
        val customer: Customer = customerRepository.save(buildCustomerDto().toEntity())
        val customerUpdateDto: CustomerUpdateDto = buildCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL/${customer.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect { MockMvcResultMatchers.status().isOk }
            .andDo(MockMvcResultHandlers.print())
    }

    private fun buildCustomerDto(
        firstName: String = "Rita",
        lastName: String = "Carvalho",
        cpf: String = "28475934625",
        email: String = "rita@gmail.com",
        password: String = "1213456",
        zipCode: String = "12345-000",
        street: String = "Rua 9",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ): CustomerDto = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        zipCode = zipCode,
        street = street,
        income = income,
    )

    private fun buildCustomerUpdateDto(
        firstName: String = "Rita",
        lastName: String = "Carvalho",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        zipCode: String = "12345-000",
        street: String = "Rua 9",

        ): CustomerUpdateDto = CustomerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        income = income,
        zipCode = zipCode,
        street = street
    )
}