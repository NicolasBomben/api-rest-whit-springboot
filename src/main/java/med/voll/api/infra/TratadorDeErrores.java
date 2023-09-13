package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;


/*actua como proxy para todos los controlles para interceptar las llamdas de errores*/
@RestControllerAdvice
public class TratadorDeErrores {
    
    /*error para manejar registo que no existe 404notfound */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    /*erro para manejar bad request cuando en el payload no viene algun dato necesario para la validacion*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manejarErro400(MethodArgumentNotValidException ex) {
    var errores = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
}

    /*dto interno para los errores de validacion */
    private record DatosErrorValidacion(String campo, String error) {

        /*constructor */
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
