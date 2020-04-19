package com.ruoyi.framework.aspectj;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.framework.util.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class CommonDataInject {

    private static Logger logger = LoggerFactory.getLogger(CommonDataInject.class);

    @Pointcut("execution(* com.ruoyi..*.mapper.*Mapper.insert*(..))")
    private void insertCutMethod() {
    }

    @Pointcut("execution(* com.ruoyi..*.mapper.*Mapper.update*(..))")
    private void updateCutMethod() {
    }

    @Around("insertCutMethod()")
    public Object doInsertAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if(arg instanceof BaseEntity){
                ((BaseEntity) arg).setCreateTime(new Date());
                if(!"insertLogininfor".equals(pjp.getSignature().getName())&&!("insertOperlog".equals(pjp.getSignature().getName()))){
                    if(ShiroUtils.getSysUser()!=null){
                        if(((BaseEntity) arg).getCreateBy()==null){
                            ((BaseEntity) arg).setCreateBy(ShiroUtils.getSysUser().getLoginName());
                        }
                    }
                }
            }
        }
        Object o = pjp.proceed();
        return o;
    }

    @Around("updateCutMethod()")
    public Object doUpdateAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if(arg instanceof BaseEntity){
                ((BaseEntity) arg).setUpdateTime(new Date());
                if(ShiroUtils.getSysUser()!=null){
                    if(((BaseEntity) arg).getUpdateBy()==null){
                        ((BaseEntity) arg).setUpdateBy(ShiroUtils.getSysUser().getLoginName());
                    }
                }
            }
        }
        Object o = pjp.proceed();
        return o;
    }
}