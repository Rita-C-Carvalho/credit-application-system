package me.dio.credit.application.system.service

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import org.aspectj.apache.bcel.classfile.Code
import java.util.UUID

interface ICreditService {

    //FUNÇÃO PARA CADASTRAR CREDITOS

    fun save(credit: Credit): Credit

    //FUNCÇÃO PARA LISTAR CREDITOS POR CUSTOMER(CLIENTE)

    fun findAllByCustomer(customerId: Long): List<Credit>

    //FUNÇÃO PARA LISTAR CREDITOS PELO CÓDIGO

    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}