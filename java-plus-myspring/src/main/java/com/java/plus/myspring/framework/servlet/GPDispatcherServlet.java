package com.java.plus.myspring.framework.servlet; 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.plus.myspring.framework.annotation.GPAutowired;
import com.java.plus.myspring.framework.annotation.GPController;
import com.java.plus.myspring.framework.annotation.GPRequestMapping;
import com.java.plus.myspring.framework.annotation.GPService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> mapping = new HashMap<String, Object>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {this.doPost(req,resp);}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	Object obj=doDispatch(req,resp);
            resp.getWriter().write(obj.toString());
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + Arrays.toString(e.getStackTrace()));
        }
    }
    private Object doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if(!this.mapping.containsKey(url)){resp.getWriter().write("404 Not Found!!");return "";}
        Method method = (Method) this.mapping.get(url);
        Map<String,String[]> params = req.getParameterMap();
        return  method.invoke(this.mapping.get(method.getDeclaringClass().getName()),new Object[]{req,resp,params.get("name")[0]});
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream is = null;
        try{
            Properties configContext = new Properties();
//            String  name=  config.getInitParameter("contextConfigLocation");
//            name=name==null?"":name;
//            is = this.getClass().getClassLoader().getResourceAsStream(name);
//            configContext.load(is);
            String scanPackage = configContext.getProperty("scanPackage");
            scanPackage=scanPackage==null?"com.java.plus.myspring":scanPackage;
            doScanner(scanPackage);
            List<String> classNameList=new ArrayList<>();
            mapping.keySet().forEach(action-> classNameList.add(action));
            for (String className : classNameList) {
                if(!className.contains(".")){continue;}
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(GPController.class)){
                    mapping.put(className,clazz.newInstance());
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                        GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (!method.isAnnotationPresent(GPRequestMapping.class)) {  continue; }
                        GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                        String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                        mapping.put(url, method);
                        System.out.println("Mapped " + url + "," + method);
                    }
                }else if(clazz.isAnnotationPresent(GPService.class)){
                        GPService service = clazz.getAnnotation(GPService.class);
                        String beanName = service.value();
                        if("".equals(beanName)){beanName = clazz.getName();}
                        Object instance = clazz.newInstance();
                        mapping.put(beanName,instance);
                        for (Class<?> i : clazz.getInterfaces()) {
                            mapping.put(i.getName(),instance);
                        }
                }else {continue;}
            }
            for (Object object : mapping.values()) {
                if(object == null){continue;}
                Class clazz = object.getClass();
                if(clazz.isAnnotationPresent(GPController.class)){
                    Field [] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if(!field.isAnnotationPresent(GPAutowired.class)){continue; }
                        GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                        String beanName = autowired.value();
                        if("".equals(beanName)){beanName = field.getType().getName();}
                        field.setAccessible(true);
                        try {
                            field.set(mapping.get(clazz.getName()),mapping.get(beanName));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) { 
            System.out.println("GP MVC Framework is init error !!! "+ e.getMessage());
        }finally {
            if(is != null){
                try {is.close();} catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.print("GP MVC Framework is init");
    }
    private void doScanner(String scanPackage) {
    	String test= scanPackage.replaceAll("\\.","/");
        URL url = this.getClass().getClassLoader().getResource(test);
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if(file.isDirectory()){ doScanner(scanPackage + "." +  file.getName());}else {
                if(!file.getName().endsWith(".class")){continue;}
                String clazzName = (scanPackage + "." + file.getName().replace(".class",""));
                mapping.put(clazzName,null);
            }
        }
    }
}