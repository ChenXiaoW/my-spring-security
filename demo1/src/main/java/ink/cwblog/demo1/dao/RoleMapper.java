package ink.cwblog.demo1.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.cwblog.demo1.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenw
 * @since 2021-05-12
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
