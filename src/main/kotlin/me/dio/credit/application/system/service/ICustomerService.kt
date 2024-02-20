package me.dio.credit.application.system.service

import me.dio.credit.application.system.entity.Customer

interface ICustomerService {

    //FUNÇÃO PARA CADASTRAR CUSTOMERS (CLIENTES)
    fun save(customer: Customer):Customer

    //FUNÇÃO PARA LISTAR CUSTOMERS PELO ID
    fun findByID(id: Long):Customer

    //FUNÇÃO PARA DELETAR CUSTOMERS PELO ID
    fun delete(id: Long)
}