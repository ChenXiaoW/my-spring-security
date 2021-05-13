package ink.cwblog.demo1.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ink.cwblog.demo1.http.ListQuery;
import ink.cwblog.demo1.http.req.LoginReq;
import ink.cwblog.demo1.http.req.RegisterUserReq;
import ink.cwblog.demo1.http.res.QueryUserRes;
import ink.cwblog.demo1.service.UserService;
import ink.cwblog.demo1.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/list")
    public Response<IPage<QueryUserRes>> queryUserList(@Valid @RequestParam ListQuery req){
        return Response.success(userService.queryUserList(req));
    }

    //TODO 未完成 by chenw 2021/05/13
    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("/detail")
    public Response<QueryUserRes> queryUserDetail(){
        return Response.success(null);
    }
}
