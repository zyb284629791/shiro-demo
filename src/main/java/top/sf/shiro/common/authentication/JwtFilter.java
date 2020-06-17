package top.sf.shiro.common.authentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String authzHeader = getAuthzHeader(request);
        log.info("authzHeader = {}",authzHeader);
        return new JwtToken(authzHeader);
    }

    /**
     * 执行登录认证(判断请求头是否带上token)
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 存在token
        if (isLoginAttempt(request, response)) {
            //如果存在,则进入executeLogin方法执行登入,检查token 是否正确
            try {
                executeLogin(request, response);return true;
            } catch (Exception e) {
                throw new AuthenticationException("Token失效请重新登录");
            }
        } else {
            // 未携带token直接返回401
            // 注意这里还没到spring，因此无法通过RestControllerAdvice来处理
            HttpServletResponse httpResponse = WebUtils.toHttp(response);
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json;charset=UTF-8");
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            PrintWriter writer = null;
            try {
                writer = httpResponse.getWriter();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.error("onLoginFailure : {}",ioException);
            }
            writer.write("当前用户未登录,请先登录!");
            return false;
        }
    }

    /**
     * 覆盖父类的方法，取消对token要求以basic开头的限制
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null ;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return false;
    }
}
