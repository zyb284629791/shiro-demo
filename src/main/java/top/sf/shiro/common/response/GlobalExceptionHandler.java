package top.sf.shiro.common.response;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 没有权限时的异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity exceptionHandler(UnauthenticatedException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    /**
     * 未登录时的异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exceptionHandler(UnauthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    /**
     * 兜底的异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
