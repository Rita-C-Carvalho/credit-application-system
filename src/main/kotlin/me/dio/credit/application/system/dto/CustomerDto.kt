package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import me.dio.credit.application.system.entity.Adress
import me.dio.credit.application.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto (
        @field:NotEmpty(message = "Campo não pode ser vazio")
        val firstName: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        val lastName: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        @field:CPF(message = "CPF inválido")
        val cpf: String,

        @field:NotNull(message = "Campo não pode ser vazio")
        val income: BigDecimal,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        @field:Email(message = "E-mail inválido")
        val email: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        @field:Size(min = 6, message = "Senha não pode ter menos que 6 caracteres")
        val password: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        val zipCode: String,

        @field:NotEmpty(message = "Campo não pode ser vazio")
        val street: String
){

fun toEntity(): Customer = Customer(
    firstName = this.firstName,
    lastName = this.lastName,
    cpf = this.cpf,
    income = this.income,
    email = this.email,
    password = this.password,
    adress = Adress(
            zipCode = this.zipCode,
            street = this.street
    )

)}

