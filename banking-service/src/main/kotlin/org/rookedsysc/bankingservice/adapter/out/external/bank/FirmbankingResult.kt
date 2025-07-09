package org.rookedsysc.bankingservice.adapter.out.external.bank

data class FirmbankingResult(
    val resultCode: Int // 0: success, 1: failure
) {
    fun isSuccess(): Boolean {
        return resultCode == 0
    }
}