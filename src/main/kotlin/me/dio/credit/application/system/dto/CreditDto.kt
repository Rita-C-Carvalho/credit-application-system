package me.dio.credit.application.system.dto

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull

data class CreditDto (
    @field:NotNull(message = "Campo não pode ser vazio")
    val creditValue: BigDecimal,

    @field:Future(message = "Data precisa ser posterior a data de hoje")
    val dayFirstInstallment: LocalDate,

    @field:NotNull(message = "Campo não pode ser vazio")
    val numberOfInstallments: Int,

    @field:NotNull(message = "Campo não pode ser vazio")
    val customerId: Long,
){
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment =  this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
