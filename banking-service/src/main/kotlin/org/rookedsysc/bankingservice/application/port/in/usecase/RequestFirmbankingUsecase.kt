package org.rookedsysc.bankingservice.application.port.`in`.usecase

import org.rookedsysc.bankingservice.application.port.`in`.dto.RequestFirmbankingCommand
import org.rookedsysc.bankingservice.domain.FirmbankingRequest

interface RequestFirmbankingUsecase {
    fun requestFirmbanking(command: RequestFirmbankingCommand): FirmbankingRequest
}