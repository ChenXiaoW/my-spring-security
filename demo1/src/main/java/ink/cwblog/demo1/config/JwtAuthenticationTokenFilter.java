package ink.cwblog.demo1.config;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.cwblog.demo1.dao.UserMapper;
import ink.cwblog.demo1.dto.UserDetailDto;
import ink.cwblog.demo1.dto.UserDto;
import ink.cwblog.demo1.exception.UnAuthorityException;
import ink.cwblog.demo1.utils.JwtUtil;
import ink.cwblog.demo1.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * jwt
 *
 * @author chenw
 * @date 2021/4/12 17:17
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenStart}")
    private String tokenStart;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的字段
        String authHeader = request.getHeader(this.tokenHeader);
        log.info("Authorization Header：{} ", authHeader);
        log.info("request path：{}", request.getRequestURI());

        if (!isInvalidAuthentication(authHeader) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String authToken = authHeader.substring(this.tokenStart.length());
            String userId = null;
            try {
                userId = jwtUtil.getIdFromToken(authToken);
            } catch (Exception e) {
                log.error("Token过期：{}", e.getMessage());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().println(JSONUtil.parse(Response.unauthorized("请先登录")));
                response.getWriter().flush();
                return;
            }
            if (StringUtils.hasText(userId)) {
                UserDto userDto = userMapper.queryUserDetailById(Integer.parseInt(userId));

                if (ObjectUtils.isEmpty(userDto)) {
                    log.info("当前用户（id:{}）不存在或者已被移除", userId);
                    throw new UnAuthorityException("当前用户不存在");
                }
                if (userDto.getStatus() == 1) {
                    log.info("当前用户（id:{}）已封禁", userId);
                    throw new UnAuthorityException("当前用户已封禁");
                }

                UserDetailDto userDetails = new UserDetailDto(userDto);
                if (jwtUtil.validateToken(authToken, userDto.getId())) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isInvalidAuthentication(String authentication) {
        return StringUtils.isEmpty(authentication) || !authentication.startsWith(this.tokenStart);
    }
}
