package me.dio.credit.application.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto (
        @field:NotEmpty(message = "Campo não pode ser vazio")
        val firstName: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        val lastName: String,

        @field:NotNull(message = "Campo não pode ser vazio")
        val income: BigDecimal,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        val zipCode: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        val street: String
){

    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.adress.zipCode = this.zipCode
        customer.adress.street = this.street
        return customer

    }

}
