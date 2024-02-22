package me.dio.credit.application.system.controller

import me.dio.credit.application.system.dto.CreditDto
import me.dio.credit.application.system.dto.CreditView
import me.dio.credit.application.system.dto.CreditViewList
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Locale.IsoCountryCode
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController (
    private val creditService: CreditService) {


    //MÉTODO PARA CADASTRAR CRÉDITO
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto):ResponseEntity <String>{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body( "Credit ${credit.creditCode} - Custumer ${credit.customer?.firstName} saved!")
    }

    //MÉTODO PARA LISTAR CRÉDITOS POR CUSTOMERS
    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>>{
        val creditViewList: List<CreditViewList> = this.creditService.findAllByCustomer(customerId).stream()
        .map{credit: Credit -> CreditViewList(credit)}
        .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }


    //MÉTODO PARA LISTAR POR CREDIT CODE
    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
       val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}