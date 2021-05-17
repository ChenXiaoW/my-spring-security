package ink.cwblog.demo1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.cwblog.demo1.http.ListQuery;
import ink.cwblog.demo1.http.req.LoginReq;
import ink.cwblog.demo1.http.req.RegisterUserReq;
import ink.cwblog.demo1.http.res.QueryUserRes;
import ink.cwblog.demo1.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenw
 * @since 2021-05-12
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param req 注册信息
     * @return 用户token
     */
    String registerUser(RegisterUserReq req);

    /**
     * 用户登录
     * @param req
     * @return 用户token
     */
    String login(LoginReq req);

    /**
     * 分页查询
     * @param req
     * @return
     */
    IPage<QueryUserRes>  queryUserList(ListQuery req);

    /**
     * 查询用户详情
     *
     * @param userId
     * @return
     */
    QueryUserRes queryUserDetail(Integer userId);

}
