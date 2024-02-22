package me.dio.credit.application.system.exception

import java.lang.Exception
import java.sql.Timestamp
import java.time.LocalDate

data class ExceptionDetails(
    val title: String,
    val timestamp: LocalDate,
    val status: Int,
    val exception: String,
    val details: MutableMap<String, String?>
)
