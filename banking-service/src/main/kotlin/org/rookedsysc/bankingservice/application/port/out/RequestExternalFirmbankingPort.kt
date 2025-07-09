package org.rookedsysc.bankingservice.application.port.out

import org.rookedsysc.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest
import org.rookedsysc.bankingservice.adapter.out.external.bank.FirmbankingResult

interface RequestExternalFirmbankingPort {
    fun requestExternalFirmbanking(request: ExternalFirmbankingRequest): FirmbankingResult
}