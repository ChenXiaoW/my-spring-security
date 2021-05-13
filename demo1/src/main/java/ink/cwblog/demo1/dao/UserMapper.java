package ink.cwblog.demo1.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.cwblog.demo1.dto.UserDto;
import ink.cwblog.demo1.http.ListQuery;
import ink.cwblog.demo1.http.res.QueryUserRes;
import ink.cwblog.demo1.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chenw
 * @since 2021-05-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return
     */
    UserDto queryUserDetailById(@Param("userId") Integer userId);

    /**
     * 分页查询
     * @param page
     * @param req
     * @return
     */
    IPage<QueryUserRes> queryUserList(Page<QueryUserRes> page, @Param(Constants.WRAPPER)ListQuery req);

}
