package com.example.exchange.repository

import com.example.exchange.model.ExchangeRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExchangeRequestRepository : JpaRepository<ExchangeRequest, Int>
