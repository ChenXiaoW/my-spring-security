package ink.cwblog.demo1.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ink.cwblog.demo1.dto.UserDetailDto;
import ink.cwblog.demo1.http.ListQuery;
import ink.cwblog.demo1.http.req.LoginReq;
import ink.cwblog.demo1.http.req.RegisterUserReq;
import ink.cwblog.demo1.http.res.QueryUserRes;
import ink.cwblog.demo1.service.UserService;
import ink.cwblog.demo1.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author chenw
 * @date 2021/5/13 11:45
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册账号
     * @param req 注册信息
     * @return 用户token
     */
    @PostMapping("/register")
    public Response<String> registerUser(@Valid @RequestBody RegisterUserReq req){
        return Response.success(userService.registerUser(req));
    }

    /**
     * 登录
     * @param req
     * @return 用户信息
     */
    @PostMapping("/login")
    public Response<String> login(@Valid @RequestBody LoginReq req){
        return Response.success(userService.login(req));
    }

    /**
     * 分页查询用户列表
     * @param req
     * @return
     */
    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/list")
    public Response<IPage<QueryUserRes>> queryUserList(@Valid @ModelAttribute ListQuery req){
        return Response.success(userService.queryUserList(req));
    }

    /**
     * 查询用户信息
     * @return
     */
    @PreAuthorize("hasAnyRole('admin','user')")
    @GetMapping("/detail")
    public Response<QueryUserRes> queryUserDetail(@AuthenticationPrincipal UserDetailDto userDetailDto){
        return Response.success(userService.queryUserDetail(userDetailDto.getId()));
    }

}
