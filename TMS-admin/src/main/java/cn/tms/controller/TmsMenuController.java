package cn.tms.controller;


import cn.hwq.vo.Result;
import cn.tms.service.TmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hwq
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/tms-menu")
public class TmsMenuController {

    @Autowired
    TmsMenuService tmsMenuService;

    @PostMapping("selectMenu")
    public Result selectMenu(){
        return tmsMenuService.selectMenu();
    }

}
