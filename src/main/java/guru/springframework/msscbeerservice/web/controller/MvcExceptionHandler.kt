package guru.springframework.msscbeerservice.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MvcExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentException(e: MethodArgumentNotValidException): ResponseEntity<List<Any>> {
        return ResponseEntity.badRequest().body(
            e.bindingResult.allErrors.map {
                val error: FieldError = it as FieldError
                "Bad Request ${error.field} : ${error.defaultMessage} : Rejected value: ${error.rejectedValue}"
            }
        )
    }

    @ExceptionHandler(BindException::class)
    fun handleBindingException(e: BindException): ResponseEntity<List<Any>> {
        return ResponseEntity.badRequest().body(e.allErrors)
    }
}