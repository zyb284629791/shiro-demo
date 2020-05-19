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
        log.info("JwtFilter-->>>isAccessAllowed-Method:init()");
        //如果请求头不存在token,则可能是执行登陆操作或是游客状态访问,直接返回true
        if (isLoginAttempt(request, response)) {
            return true;
        }
        //如果存在,则进入executeLogin方法执行登入,检查token 是否正确
        try {
            executeLogin(request, response);return true;
        } catch (Exception e) {
            throw new AuthenticationException("Token失效请重新登录");
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = httpResponse.getWriter();
        writer.write("{\"errCode\": 401, \"msg\": \"UNAUTHORIZED\"}");
        return executeLogin(request,response);
    }


}
