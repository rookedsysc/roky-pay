package org.rookedsysc.membershipservice.aop

import io.swagger.v3.oas.annotations.tags.Tag

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Tag(name = "Membership", description = "멤버쉽 관리 API")
annotation class MembershipTag
