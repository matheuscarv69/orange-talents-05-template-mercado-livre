package mercadoLivre.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionFormDto> handleValidationException(MethodArgumentNotValidException exception) {
        List<ExceptionFormDto> errorsDtoList = new ArrayList<>();

        List<FieldError> fieldErrorsList = exception.getBindingResult().getFieldErrors();

        fieldErrorsList.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ExceptionFormDto erroDto = new ExceptionFormDto(fieldError.getField(), message);
            errorsDtoList.add(erroDto);
        });

        return errorsDtoList;
    }

}
