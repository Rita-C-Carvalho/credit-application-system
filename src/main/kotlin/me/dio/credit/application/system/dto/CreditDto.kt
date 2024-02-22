package me.dio.credit.application.system.dto

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate
import jakarta.persistence.*
import java.util.UUID
import me.dio.credit.application.system.enummeration.Status

data class CreditDto (
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long,
){
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment =  this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
