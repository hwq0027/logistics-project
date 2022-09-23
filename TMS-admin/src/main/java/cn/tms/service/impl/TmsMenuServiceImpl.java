package cn.tms.service.impl;

import cn.hwq.vo.Result;
import cn.tms.mapper.TmsMenuMapper;
import cn.hwq.pojo.TmsMenu;
import cn.tms.service.TmsMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hwq
 * @since 2022-09-22
 */
@Service
public class TmsMenuServiceImpl extends ServiceImpl<TmsMenuMapper, TmsMenu> implements TmsMenuService {

    @Override
    public Result selectMenu() {
        System.out.println("jadsfadsf");
        List<TmsMenu> list = list();
        HashMap<String, List> hashMap=new HashMap<>();

        List<TmsMenu> collect1 = list.stream().filter(item -> item.getMenuLevel().equals("1")).collect(Collectors.toList());
        if (collect1.size()>1){
            hashMap.put("一级菜单",collect1);
        }

        List<TmsMenu> collect2 = list.stream().filter(item -> item.getMenuLevel().equals("2")).collect(Collectors.toList());
        if (collect1.size()>1){
            hashMap.put("二级菜单",collect2);
        }

        List<TmsMenu> collect3 = list.stream().filter(item -> item.getMenuLevel().equals("3")).collect(Collectors.toList());
        if (collect1.size()>1){
            hashMap.put("三级菜单",collect3);
        }


        return Result.ok(hashMap);
    }

}
