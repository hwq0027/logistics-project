package com.docker.mapper;

import com.docker.pojo.PdAuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xdz
 * @since 2022-10-09
 */
@Mapper
public interface PdAuthUserMapper extends BaseMapper<PdAuthUser> {

//    PdAuthUser findByuser(int id)
}
