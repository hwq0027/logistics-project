package cn.hwq;

import cn.hutool.Hutool;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hwq.jwt.JwtUtil;
import cn.hwq.utils.BaiDuResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import org.assertj.core.error.ShouldBeToday;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;

public class Test01 {

    private String ak = "64Ut0Peo2Dsb1l43FRl1nReM0tBdpE3L";
    /**
     * 测试地点输入提示服务
     *
     *
     */
    @Test

    public void testSuggestion(){
        String loaction1="112.893839,28.119959";
        String loaction2="113.36876,23.126704";
        //String url = "https://api.map.baidu.com/place/v2/suggestion?query=清华大&region=北京&city_limit=true&output=json&ak={}";
        //String url ="https://api.map.baidu.com/place/v2/search?query=员村新街号&tag=天河区&region=广州市&output=json&ak={}";
        //String url="https://api.map.baidu.com/routematrix/v2/driving?output=json&origins=40.45,116.34&destinations=40.34,116.45&ak={}";  //GET请求
        String url="https://api.map.baidu.com/routematrix/v2/driving?output=json&origins={}&destinations={}&ak={}";  //GET请求

        url = StrUtil.format(url,loaction1, loaction2,ak);
        System.out.println(url);
        String body = HttpRequest.get(url).execute().body();
        System.out.println(body);
        HashMap map = JSONUtil.toBean(body, HashMap.class);
        String status = String.valueOf(map.get("result"));


        String substring = status.substring(1,status.length());
        String substring1 = substring.substring(0,substring.length()-1);
        JSONObject jsonObject1 = JSONUtil.parseObj(substring1);
        String text = jsonObject1.getStr("distance");
        HashMap map1 = JSONUtil.toBean(text, HashMap.class);
        System.out.println(map1.get("text"));
        //BaiDuResult(address=天河区, name=员村新街57号, location={lng=113.36876, lat=23.126704})
    }







    public void test2(){
        String jwt = JwtUtil.createJWT("95214683308425218", 24 * 60 * 60 * 1000L);
        System.out.println(jwt);
    }

    public void test(){
        FastAutoGenerator
                //创建连接
                .create("jdbc:mysql://101.34.103.51:3306/ls_db_order?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8", "root", "123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("hwq") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("cn.hwq") // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .entity("pojo")//设置实体类包命,默认为entity
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/java/cn/hwq/mapper/xml")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            //.likeTable(null)//匹配该数据库下所有表,与Include选项二存一
                            .addInclude("pd_order") // 设置需要生成的表名
                            .addTablePrefix("pd_") // 设置过滤表前缀
                            .serviceBuilder()   // service 配置策略
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok() // 开启Lombok
                            .logicDeleteColumnName("deleted")   // 说明逻辑删除（软删除）是哪个字段
                            .enableTableFieldAnnotation()   // 属性上加说明注解
                            .controllerBuilder()    // controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle()  // 开启 RestController
                            .mapperBuilder()    //mapper 策略配置
                            .superClass(BaseMapper.class)   // 继承哪个父类
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()   // @mapper 开启
                            .formatXmlFileName("%sMapper"); // xml 名


                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
