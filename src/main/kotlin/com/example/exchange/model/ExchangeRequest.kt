package com.example.exchange.model

import jakarta.persistence.*

@Entity
@Table(name = "ExchangeRequests")
data class ExchangeRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "desired_rooms")
    val desiredRooms: Int,

    @Column(name = "desired_area")
    val desiredArea: Double,

    @Column(name = "desired_floor")
    val desiredFloor: Int,

    @Column(name = "desired_district")
    val desiredDistrict: String,

    @Column(name = "available_rooms")
    val availableRooms: Int,

    @Column(name = "available_area")
    val availableArea: Double,

    @Column(name = "available_floor")
    val availableFloor: Int,

    @Column(name = "available_district")
    val availableDistrict: String,

    @Column(name = "username")
    val username: String
) {
    constructor() : this(null, 0, 0.0, 0, "", 0, 0.0, 0, "", "")
}