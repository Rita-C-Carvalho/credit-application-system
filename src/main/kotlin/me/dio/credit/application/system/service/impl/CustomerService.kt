package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
        private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findByID(id: Long): Customer =
            this.customerRepository.findById(id).orElseThrow(){
                throw RuntimeException("Id $id not found!")
            }

    override fun delete(id: Long) {
        val customer: Customer = this.findByID(id)
        this.customerRepository.delete(customer)
    }
}