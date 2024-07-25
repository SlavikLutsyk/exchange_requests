package com.example.exchange.service

import com.example.exchange.model.ExchangeRequest
import com.example.exchange.repository.ExchangeRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExchangeRequestService(@Autowired private val exchangeRequestRepository: ExchangeRequestRepository) {

    fun processExchangeRequests(newRequest: ExchangeRequest): ExchangeRequest {
        for (existingRequest in exchangeRequestRepository.findAll()) {
            if (isExchangePossible(newRequest, existingRequest) && newRequest.username != existingRequest.username) {
                exchangeRequestRepository.delete(existingRequest)
                return existingRequest
            }
        }
        exchangeRequestRepository.save(newRequest)
        return ExchangeRequest()
    }

    private fun isExchangePossible(newRequest: ExchangeRequest, existingRequest: ExchangeRequest): Boolean {
        val areaDifference1 = Math.abs(newRequest.desiredArea - existingRequest.availableArea)
        val areaDifference2 = Math.abs(existingRequest.desiredArea - newRequest.availableArea)
        val areaThreshold1 = newRequest.desiredArea * 0.1
        val areaThreshold2 = existingRequest.desiredArea * 0.1
        val areasMatch = areaDifference1 <= areaThreshold1 && areaDifference2 <= areaThreshold2

        return newRequest.desiredRooms == existingRequest.availableRooms &&
                newRequest.desiredFloor == existingRequest.availableFloor &&
                newRequest.desiredDistrict.split(' ')[0] == existingRequest.availableDistrict.split(' ')[0] &&
                newRequest.availableRooms == existingRequest.desiredRooms &&
                newRequest.availableFloor == existingRequest.desiredFloor &&
                newRequest.availableDistrict.split(' ')[0] == existingRequest.desiredDistrict.split(' ')[0] &&
                areasMatch
    }

    fun getAllExchangeRequests(): List<ExchangeRequest> {
        return exchangeRequestRepository.findAll()
    }

    fun updateExchangeRequest(request: ExchangeRequest, id: Int): ExchangeRequest {
        if (exchangeRequestRepository.existsById(id)) {
            return exchangeRequestRepository.save(request)
        } else {
            throw Exception("ExchangeRequest not found")
        }
    }

    fun deleteExchangeRequest(id: Int) {
        if (exchangeRequestRepository.existsById(id)) {
            exchangeRequestRepository.deleteById(id)
        } else {
            throw Exception("ExchangeRequest not found")
        }
    }
    fun getExchangeRequestById(id: Int): ExchangeRequest {
        return exchangeRequestRepository.findById(id).orElseThrow {
            throw NoSuchElementException("Exchange request with id $id not found")
        }
    }
}

