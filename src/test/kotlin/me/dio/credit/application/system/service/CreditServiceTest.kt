package me.dio.credit.application.system.service

import me.dio.credit.application.system.service.impl.CreditService
import me.dio.credit.application.system.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.dio.credit.application.system.entity.Adress
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.enummeration.Status
import me.dio.credit.application.system.repository.CreditRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK
    lateinit var creditRepository: CreditRepository

    @MockK
    lateinit var customerService: CustomerService

    @InjectMockKs
    lateinit var creditService: CreditService

    @Test
    fun `should save credit successfully`() {
        // Given
        val fakeCredit = buildCredit()
        val mockKAdditionalAnswerScope =
            every { customerService.findByID(fakeCredit.customer?.id!!) } returns fakeCredit.customer!!
        every { creditRepository.save(any()) } returns fakeCredit

        // When
        val actual = creditService.save(fakeCredit)


        //then - é a parte das assertivas, vamos ver se de fato está tendo o retorno q esperamos
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit)}
    }

    @Test
    fun `should find all credits by customer`() {
        // Given
        val customerId = 1L
        val fakeCredits = listOf(buildCredit(customer = buildCustomer()))
        every { creditRepository.findAllByCustomer(customerId) } returns fakeCredits

        // When
        val actualCredits = creditService.findAllByCustomer(customerId)

        // Then
        Assertions.assertThat(actualCredits).isNotEmpty
        Assertions.assertThat(actualCredits).containsExactlyElementsOf(fakeCredits)
        verify(exactly = 1) { creditRepository.findAllByCustomer(customerId) }
    }



    @Test
    fun `should find credit by credit code and customer id`() {
        // Given
        val customerId = 1L
        val creditCode = UUID.randomUUID()
        val fakeCredit = buildCredit(customer = buildCustomer(), creditCode = creditCode)
        every { creditRepository.findByCreditCode(creditCode) } returns fakeCredit

        // When
        val actualCredit = creditService.findByCreditCode(customerId, creditCode)

        // Then
        Assertions.assertThat(actualCredit).isNotNull
        Assertions.assertThat(actualCredit).isEqualToComparingFieldByField(fakeCredit)
        verify(exactly = 1) { creditRepository.findByCreditCode(creditCode) }
    }

    private fun buildCustomer(
        firstName: String = "Rita",
        lastName: String = "Carvalho",
        cpf: String = "28475934625",
        email: String = "rita@gmail.com",
        password: String = "1213456",
        zipCode: String = "12345-000",
        street: String = "Rua 9",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        adress = Adress(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )
    private fun buildCredit(
        creditCode: UUID = UUID.randomUUID(),
        creditValue: BigDecimal = BigDecimal.valueOf(1000.0),
        dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(1),
        numberOfInstallments: Int = 12,
        status: Status = Status.IN_PROGRESS,
        customer: Customer? = buildCustomer(),
        id: Long? = 1L
    ): Credit {
        return Credit(
            creditCode = creditCode,
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            status = status,
            customer = customer,
            id = id
        )
    }}