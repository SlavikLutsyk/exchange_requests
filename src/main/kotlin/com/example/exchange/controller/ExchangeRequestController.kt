package com.example.exchange.controller

import com.example.exchange.model.ExchangeRequest
import com.example.exchange.service.ExchangeRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/exchange-requests")
class ExchangeRequestController(@Autowired private val exchangeRequestService: ExchangeRequestService) {

    @PostMapping("/process")
    fun processExchangeRequests(@RequestBody request: ExchangeRequest): ResponseEntity<ExchangeRequest> {
        println(request.id)
        println(request.username)
        val processedRequest = exchangeRequestService.processExchangeRequests(request)
        return ResponseEntity.ok(processedRequest)
    }

    @GetMapping("/all")
    fun getAllExchangeRequests(): ResponseEntity<List<ExchangeRequest>> {
        val allRequests = exchangeRequestService.getAllExchangeRequests()
        return ResponseEntity.ok(allRequests)
    }

    @PutMapping("/update/{id}")
    fun updateExchangeRequest(@RequestBody request: ExchangeRequest, @PathVariable id: Int): ResponseEntity<ExchangeRequest> {
        val updatedRequest = exchangeRequestService.updateExchangeRequest(request, id)
        return ResponseEntity.ok(updatedRequest)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteExchangeRequest(@PathVariable id: Int): ResponseEntity<Void> {
        exchangeRequestService.deleteExchangeRequest(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getExchangeRequestById(@PathVariable id: Int): ResponseEntity<ExchangeRequest> {
        val request = exchangeRequestService.getExchangeRequestById(id)
        return ResponseEntity.ok(request)
    }

}