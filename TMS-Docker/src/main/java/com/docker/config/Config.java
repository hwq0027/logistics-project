package com.docker.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Config {
    public static void main(String[] args) {

        FastAutoGenerator
                //创建连接
                .create("jdbc:mysql://101.34.103.51:3306/ls_db_base?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8", "root", "123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("xdz") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.docker") // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .entity("pojo")//设置实体类包命,默认为entity
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/java/com/docker/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            // .likeTable(null)//匹配该数据库下所有表,与Include选项二存一
                           .addInclude("pd_transport_trips_truck_driver") // 设置需要生成的表名
                            .addTablePrefix("", "") // 设置过滤表前缀
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
