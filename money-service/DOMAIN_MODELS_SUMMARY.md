# Money Service Domain Models Summary

## Overview
This document summarizes the Kotlin domain models created for the money service based on the fastcampus-pay patterns and following the roky-pay conventions.

## Domain Models Created

### 1. MemberMoney.kt
- **Purpose**: Represents a member's money/balance information
- **Key Features**:
  - Immutable domain entity with validation
  - Uses `MoneyAmount` value object for type-safe money operations
  - Charge and use operations with business rule validation
  - Balance sufficiency checks
  - Follows roky-pay naming conventions

### 2. MoneyChangingRequest.kt
- **Purpose**: Represents requests to change money balance (charging/withdrawing)
- **Key Features**:
  - Tracks money changing requests with status management
  - Immutable state transitions (pending → success/failed)
  - Type-safe operations with validation
  - Comprehensive status checking methods

### 3. MoneyTransactionHistory.kt
- **Purpose**: Represents transaction history for money operations
- **Key Features**:
  - Immutable transaction records
  - Links to original money changing requests
  - Transaction summary and reporting capabilities
  - Type-safe money amount handling

### 4. MoneyAmount.kt (Value Object)
- **Purpose**: Type-safe money amount representation
- **Key Features**:
  - Immutable value object following DDD principles
  - Safe arithmetic operations (add, subtract, multiply, divide)
  - Proper formatting and validation
  - Currency handling with constants

### 5. MoneyChangingType.kt (Enum)
- **Purpose**: Defines types of money changes
- **Values**: `CHARGE`, `USE`

### 6. MoneyChangingStatus.kt (Enum)
- **Purpose**: Defines status of money changing requests
- **Values**: `PENDING`, `SUCCESS`, `FAILED`

### 7. MoneyDomainFactory.kt
- **Purpose**: Factory for creating domain objects
- **Key Features**:
  - Centralizes object creation logic
  - Provides default values and UUID generation
  - Reduces boilerplate code

## Supporting Infrastructure

### 8. ValidationUtils.kt
- **Purpose**: Validation utility following roky-pay patterns
- **Features**: Jakarta Bean Validation integration

### 9. MoneyConstants.kt
- **Purpose**: Business constants for money operations
- **Features**: Min/max amounts, currency settings, decimal places

## Architecture Compliance

### Hexagonal Architecture
- Domain models are pure business logic
- No external dependencies in domain layer
- Proper separation of concerns

### Roky-Pay Conventions
- ✅ Kotlin coding style (4-space indentation)
- ✅ Jakarta Bean Validation usage
- ✅ Immutable domain objects
- ✅ Proper package structure
- ✅ ValidationUtils pattern
- ✅ Constants management
- ✅ Korean comments for business logic

### Validation Patterns
- Field-level validation with annotations
- Business rule validation in methods
- Fail-fast validation in constructors
- Comprehensive error messages

## Usage Examples

```kotlin
// Create member money
val memberMoney = MoneyDomainFactory.createMemberMoney(
    memberId = "member123",
    linkedBankAccount = "account456",
    aggregateIdentifier = "aggregate789"
)

// Charge money
val chargeAmount = MoneyAmount.of("50000")
val chargedMoney = memberMoney.charge(chargeAmount)

// Create changing request
val changingRequest = MoneyDomainFactory.createMoneyChangingRequest(
    targetMembershipId = "member123",
    changingType = MoneyChangingType.CHARGE,
    changingMoneyAmount = chargeAmount
)

// Mark as success
val successRequest = changingRequest.markAsSuccess()
```

## Integration Points

These domain models are designed to integrate with:
- Membership service (for member validation)
- Banking service (for account operations)
- Event-driven architecture (for status changes)
- CQRS pattern (for read/write operations)

## Future Enhancements

Consider adding:
- Domain events for money changes
- Audit trail support
- Currency conversion support
- Transaction fee calculations
- Batch operations support