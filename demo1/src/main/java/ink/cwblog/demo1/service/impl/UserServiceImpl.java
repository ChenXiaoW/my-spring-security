package ink.cwblog.demo1.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.cwblog.demo1.dao.UserMapper;
import ink.cwblog.demo1.exception.BusinessException;
import ink.cwblog.demo1.http.ListQuery;
import ink.cwblog.demo1.http.req.LoginReq;
import ink.cwblog.demo1.http.req.RegisterUserReq;
import ink.cwblog.demo1.http.res.QueryUserRes;
import ink.cwblog.demo1.pojo.User;
import ink.cwblog.demo1.service.UserService;
import ink.cwblog.demo1.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenw
 * @since 2021-05-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 用户注册
     *
     * @param req 注册信息
     * @return 用户token
     */
    @Override
    public String registerUser(RegisterUserReq req) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getTel, req.getTel()).isNull(User::getDeleteTime));
        if (ObjectUtils.isNotEmpty(user)){
            throw new BusinessException("当前用户已注册");
        }
        String password = passwordEncoder.encode(req.getPassword());
        user = new User().setName(req.getName()).setPassword(password).setTel(req.getTel());
        userMapper.insert(user);
        return jwtUtil.generateToken(user.getId());
    }

    /**
     * 用户登录
     *
     * @param req
     * @return 用户token
     */
    @Override
    public String login(LoginReq req) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getTel, req.getTel()).isNull(User::getDeleteTime));
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException("用户手机号错误");
        }
        if (!passwordEncoder.matches(req.getPassword(),user.getPassword())){
            throw new BusinessException("用户密码错误");
        }
        String token = jwtUtil.generateToken(user.getId());
        return token;
    }

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    @Override
    public IPage<QueryUserRes> queryUserList(ListQuery req) {
        Page<QueryUserRes> page = new Page<>(req.getPageNum(),req.getPageSize());
        return userMapper.queryUserList(page,req);
    }

    /**
     * 查询用户详情
     *
     * @param userId
     * @return
     */
    @Override
    public QueryUserRes queryUserDetail(Integer userId) {
        return userMapper.queryUserDetail(1);
    }
}
