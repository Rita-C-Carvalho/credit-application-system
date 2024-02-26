package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val custumerService: CustomerService
): ICreditService {

    //FUNÇÃO PARA CADASTRAR CREDITOS
    override fun save(credit: Credit): Credit {
        credit.apply {
            //VERIFICANDO SE O CUSTOMER DESSE CREDITO EXISTE
            customer = custumerService.findByID(credit.customer?.id!!)
        }
        //SALVANDO O CREDITO
        return this.creditRepository.save(credit)
    }


    //FUNCÇÃO PARA LISTAR CREDITOS POR CUSTOMER(CLIENTE)
    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomer(customerId)

    //FUNÇÃO PARA LISTAR CREDITOS PELO CÓDIGO
    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Creditcode $creditCode not fond"))
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }
}