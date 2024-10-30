package com.kh.damgarak.common.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggingAOP {
	@Pointcut("execution(* com.kh.damgarak..controller.*.*(..) )")
	private void cut() {}
	
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		MethodSignature ms = (MethodSignature)joinPoint.getSignature();
		java.lang.reflect.Method method = ms.getMethod();
		
		Object[] args = joinPoint.getArgs();
		
		System.out.println("==================== Before Method ==================");
		System.out.println("* information     : " + ms);
		System.out.println("* method name     : " + method);
		System.out.println("* parameter       : " + Arrays.toString(args));
	}
	
	@After("cut()")
	public void after() {
		System.out.println("");
	}
	
	
	@Around("cut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 메소드 호출되기 전 시간 측정
		long startTime = System.currentTimeMillis();
		
		Object work = pjp.proceed();			// 원래 메소드가 수행할 기능
		
		// 메소드 호출 후 시간 측정
		long endTime = System.currentTimeMillis();
		
		long time = endTime - startTime;
		
		System.out.println("-------------------- Around Method ----------------");
		System.out.println("* information     : " + pjp.getSignature());
		System.out.println("* processing time : " + time + "ms");
		
		return work;
	}
}
