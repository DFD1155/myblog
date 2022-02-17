package com.dfd.vueblog_java;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class DengCode {
    public static void main(String[] args) {
//        构建代码自动生成器对象
        AutoGenerator atg = new AutoGenerator();
//        配置策略
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");

//        1、全局配置
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("Deng");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setIdType(IdType.ID_WORKER);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setSwagger2(true);

//        2、设置数据源
        DataSourceConfig dc = new DataSourceConfig();
        dc.setUrl("jdbc:mysql://localhost:3306/vueblog?useSSL=false&useUnicode=ture&characterEncoding=utf-8&serverTimezone=GMT%2b8");
        dc.setDriverName("com.mysql.cj.jdbc.Driver");
        dc.setUsername("root");
        dc.setPassword("root");
        dc.setDbType(DbType.MYSQL);

//        3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("vueblog_java");
        pc.setParent("com.dfd");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");

//        4、策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("m_blog");
        sc.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        sc.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        sc.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        sc.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
        sc.setRestControllerStyle(true); //restful api风格控制器
        sc.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

//        5、放入自动生成对象
        atg.setGlobalConfig(globalConfig);
        atg.setDataSource(dc);
        atg.setPackageInfo(pc);
        atg.setStrategy(sc);

//        6、执行
        atg.execute();

    }
}
