package com.ruoyi.web.plugin;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.util.Date;
import java.util.Properties;

/**
 * 自定义 Mybatis 插件，自动设置 createTime 和 updatTime 的值。
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class CustomInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数
        Object parameter = invocation.getArgs()[1];
        if (parameter != null) {
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {// insert 语句插入 createTime
                if(parameter instanceof BaseEntity){
                    ((BaseEntity) parameter).setCreateTime(new Date());
                    ((BaseEntity) parameter).setCreateBy(ShiroUtils.getSysUser().getUserName());
                }
            }
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                if(parameter instanceof BaseEntity){
                    ((BaseEntity) parameter).setUpdateTime(new Date());
                    ((BaseEntity) parameter).setUpdateBy(ShiroUtils.getSysUser().getUserName());
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}