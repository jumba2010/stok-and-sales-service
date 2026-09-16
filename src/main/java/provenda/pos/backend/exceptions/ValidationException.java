package provenda.pos.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationException extends BusinessException{


   public ValidationException(String code, String message) {

       super(code, message);

    }


}
