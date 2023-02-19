package com.natwest.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LoggerAspect {
	Logger log = LoggerFactory.getLogger(this.getClass());

	// this is run before the method execution
	@Before(value = "execution(* com.natwest.controller.TransactionController.addTrans(..))")
	public void logBeforeAddTrans(JoinPoint joinPoint) {
		log.info("***************");
		log.info("Method name executed: " + joinPoint.getSignature().getDeclaringTypeName()
				+ joinPoint.getSignature().getName());
	}

	@After(value = "execution(* com.natwest.controller.TransactionController.addTrans(..))")
	public void logAfterAddTrans(JoinPoint joinPoint) {
		log.info("***************");
		log.info("Method arguments are: " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(value = "execution(* com.natwest.controller.TransactionController.addTrans(..))", throwing = "e")
	public void logAfterAddTransExceptionOccured(JoinPoint joinPoint, Throwable e) {
		log.info("****** Exception occured *********");
		log.error(e.getMessage());
	}

	@AfterThrowing(value = "execution(* com.natwest.controller.TransactionController.getTransaction(..))", throwing = "e")
	public void logAfterGetTransactionExceptionOccured(JoinPoint joinPoint, Throwable e) {
		log.info("****** Exception occured *********");
		log.error("Exception cause" + e.getCause());
		log.error(e.getMessage());
	}

	@Around(value = "execution(* com.natwest.controller.TransactionController.addTrans(..))")
	public Object logAroundEntireApplication(ProceedingJoinPoint joinPoint) throws Throwable {

		log.info("Method name: " + joinPoint.getSignature().getDeclaringTypeName());
		log.info("Method arguments:" + joinPoint.getArgs());
		log.info("**************Around advice called***********************");

		// we use the object to store the output using the proceed() method
		Object obj;
		try {
			obj = joinPoint.proceed();

			log.info("Output:" + obj);

			return obj;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	// this will be injected when the method is executed
	@Around(value = "execution(* com.natwest.controller.TransactionController.fetchAll(..))")
	public Object logAroundAddTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("*********** Around advice called ***************");
		log.info("Method name executed: " + joinPoint.getSignature().getDeclaringTypeName());

		Object obj;

		try {
			obj = joinPoint.proceed();
			log.info("Method output: " + obj);
			return obj;
		} catch (Exception ex) {
			throw ex;

		}

	}

}
