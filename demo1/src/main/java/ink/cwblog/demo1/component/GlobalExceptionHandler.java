package ink.cwblog.demo1.component;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import ink.cwblog.demo1.exception.BusinessException;
import ink.cwblog.demo1.exception.UnAuthorityException;
import ink.cwblog.demo1.vo.ClientError;
import ink.cwblog.demo1.vo.Response;
import ink.cwblog.demo1.vo.ServerError;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.access.AccessDeniedException;

/**
 * 全局异常捕获
 * @author chenw
 * @date 2021/4/1 16:22
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //当前环境
    @Value("spring.profiles.active")
    private String active;

    /**
     * 判断当前环境
     * @return true - master环境 ,false - 其他环境
     */
    private Boolean isOutput(){
        return "master".equals(active)?true:false;
    }

    /**
     * Exception 异常捕获
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Response<T> globalExceptionHandler(Exception e) {
        e.printStackTrace();
        if (this.isOutput()) {
            return Response.fail(ServerError.SERVER_ERROR, ServerError.SERVER_ERROR.getMsg());
        }
        return Response.fail(ServerError.SERVER_ERROR, e.getMessage());
    }

    /**
     * @valid校验 异常捕获
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public <T> Response<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        //存在错误信息
        if (!this.isOutput() && bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (CollectionUtils.isNotEmpty(allErrors)){
                List<String> errorInfo =  new ArrayList<>(10);
                allErrors.stream().forEach(item -> errorInfo.add(item.getDefaultMessage()));
                return Response.fail(ClientError.PARAM_ERROR, JSON.toJSONString(errorInfo));
            }
        }
        return Response.fail(ClientError.PARAM_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Response requestBodyMissingHandler(HttpMessageNotReadableException e) {
        if (!this.isOutput()){
            return Response.fail(ClientError.PARAM_ERROR, e.getMessage());
        }
        return Response.fail(ClientError.PARAM_ERROR);
    }

    /**
     * BusinessException 业务异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public <T> Response<T> businessExceptionHandler( BusinessException e) {
        e.printStackTrace();
        return Response.fail(e);
    }

    /**
     * AccessDeniedException 权限异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public <T> Response<T> accessDeniedExceptionHandler(AccessDeniedException e) {
        return Response.fail(ClientError.FORBIDDEN, e.getMessage());
    }

    /**
     * ExpiredJwtException token过期
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public <T> Response<T> expiredJwtException(ExpiredJwtException e){
        return Response.fail(ClientError.UNAUTHORIZED,ClientError.UNAUTHORIZED.getMsg());
    }

    /**
     * 无权限异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(UnAuthorityException.class)
    @ResponseBody
    public <T> Response<T> unAuthorityException(UnAuthorityException e){
        return Response.fail(ClientError.UNAUTHORIZED,e.getMessage());
    }

    /**
     * Assert 校验异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public <T> Response<T> illegalStateException(IllegalStateException e){
        log.error("参数校验异常：{}",e);
        return Response.fail(ClientError.PARAM_ERROR,e.getMessage());
    }
}
