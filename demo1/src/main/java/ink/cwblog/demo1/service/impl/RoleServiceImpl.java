package ink.cwblog.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.cwblog.demo1.dao.RoleMapper;
import ink.cwblog.demo1.pojo.Role;
import ink.cwblog.demo1.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
