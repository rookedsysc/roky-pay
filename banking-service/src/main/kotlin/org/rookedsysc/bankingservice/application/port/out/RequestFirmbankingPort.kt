package org.rookedsysc.bankingservice.application.port.out

import org.rookedsysc.bankingservice.domain.FirmbankingRequest

interface RequestFirmbankingPort {
    fun createFirmbankingRequest(firmbankingRequest: FirmbankingRequest): FirmbankingRequest
    fun updateFirmbankingRequest(firmbankingRequest: FirmbankingRequest): FirmbankingRequest
    fun findById(id: String): FirmbankingRequest?
}