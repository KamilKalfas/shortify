package com.kkalfas.shortly.history.data

/**
 * Exception thrown by methods that should not be called
 * i.e. some local data source method are not available for remote and vice versa
 */
class FunctionalityNotAvailable : Exception("This functionality is not available")

/**
 * Exception wrapping errors from [ShrtcoApi]
 */
class ShrtcoApiError(val errorCode: Int, val errorMessage: String) : Exception("Error: $errorCode. $errorMessage")
