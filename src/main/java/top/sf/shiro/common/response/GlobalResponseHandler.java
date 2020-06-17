package top.sf.shiro.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (!ObjectUtils.isEmpty(returnType.getMethod()) && returnType.getMethod().isAnnotationPresent(IgnoreGlobalResponse.class)) {
            return false;
        }
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreGlobalResponse.class)) {
            return false;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (ObjectUtils.isEmpty(body)) {
            return ResponseEntity.ok().build();
        }
        if (body instanceof ResponseEntity) {
            return body;
        }
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(ResponseEntity.ok(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(body);
    }
}
