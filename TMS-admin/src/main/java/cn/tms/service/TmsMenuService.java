package cn.tms.service;

import cn.hwq.pojo.TmsMenu;
import cn.hwq.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hwq
 * @since 2022-09-22
 */
public interface TmsMenuService extends IService<TmsMenu> {

    Result selectMenu();

}
