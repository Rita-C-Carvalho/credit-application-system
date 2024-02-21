package me.dio.credit.application.system.controller

import me.dio.credit.application.system.dto.CustomerDto
import me.dio.credit.application.system.dto.CustomerView
import me.dio.credit.application.system.dto.CustumerUpdateDto
import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.service.impl.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController (
        private val customerService: CustomerService
){
    //MÉTODO PARA CADASTRAR CUSTOMERS

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): String{
       val savedCustomer = this.customerService.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }


    //MÉTODO PARA LISTAR CUSTOMERS PELO ID
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CustomerView {
        val customer: Customer = this.customerService.findByID(id)
        return CustomerView(customer)
    }

    //MÉTODO PARA DELETAR CUSTOMERS PELO ID
    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    //MÉTODO PARA ATUALIZAR CUSTOMERS

    //O @PatchMapping serve para quand queremos atualizar poucos dados, quando queremos fazer a alteração completa, usa-se o @PutMapping
    @PatchMapping
    fun updateCustumer(@RequestParam(value = "custoerId") id: Long,
                       @RequestBody customerUpdateDto: CustumerUpdateDto) : CustomerView{

        val customer: Customer = this.customerService.findByID(id)
        val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)
        return CustomerView(customerUpdated)
    }
}