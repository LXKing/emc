package com.huak.log;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.log.model.OperateLog;

@Aspect
public class OperateLogger {
	
	@Autowired
	private OperateLogService logService;

	@Around("execution(* com.huak..*Controller.*(..))")
	public Object operateLogger(ProceedingJoinPoint jp) throws Throwable {
		//获取插入点信息
		Signature sig = jp.getSignature();
		MethodSignature msig = null;
		if(!(sig instanceof MethodSignature)){
			throw new IllegalArgumentException("方法调用异常");
		}
		msig = (MethodSignature)sig;
		OperateLog log = new OperateLog();
		//获取操作的类名称
		String className = jp.getTarget().getClass().getName();
		//获取操作的方法名称
		Method method = msig.getMethod();
		String methodName = method.getName();
		//获取日志注解中的描述信息
		String optKey = method.getAnnotation(EMCLog.class)==null?"":method.getAnnotation(EMCLog.class).key();
		String optType = method.getAnnotation(EMCLog.class)==null?"":method.getAnnotation(EMCLog.class).type();
		String optName = method.getAnnotation(EMCLog.class)==null?"":method.getAnnotation(EMCLog.class).name();
		//获取request中的信息
		Object[] params = jp.getArgs();
		HttpServletRequest request = null;
		for(Object param:params){
			if(param instanceof HttpServletRequest){
				request = (HttpServletRequest)param;break;
			}
		}
		if(request == null){
			request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		}
		if(request!=null){
			String ip = getIpAddr(request);
			String host = request.getRemoteHost();
			String port = request.getRemotePort()+"";
			String uri = request.getRequestURI();
			String url = request.getRequestURL().toString();
			log.setRemoteIp(ip);
			log.setRemoteName(host);
			log.setRemotePort(port);
			log.setReqUri(uri);
			log.setReqUrl(url);
		}
		log.setId(UUIDGenerator.getUUID());
		log.setOptName(optName);
		log.setOptKey(optKey);
		log.setOptType(optType);
		log.setClassName(className);
		log.setMethodName(methodName);
		log.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		log.setCreator(request.getSession().getAttribute(Constants.SESSION_KEY)==null
				?"":request.getSession().getAttribute(Constants.SESSION_KEY).toString());
		logService.saveOperateLog(log);
		return jp.proceed();
	}
	
	/**
	 * 获取真实ip地址
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request){  
	    String ipAddress = request.getHeader("x-forwarded-for");  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getRemoteAddr();  
	            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
	                //根据网卡取本机配置的IP  
	                InetAddress inet=null;  
	                try {  
	                    inet = InetAddress.getLocalHost();  
	                } catch (UnknownHostException e) {  
	                    e.printStackTrace();  
	                }  
	                ipAddress= inet.getHostAddress();  
	            }  
	        }  
	        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
	            if(ipAddress.indexOf(",")>0){  
	                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
	            }  
	        }  
	        return ipAddress;   
	}  
}
