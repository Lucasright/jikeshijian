package com;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader{

	protected Class<?> findClass(String name){
		File directory = new File("."); 
		String xlassPath = null;
		byte[] bytes = null;
        Path path = null;
        Class<?> clazz;
		try {
			xlassPath = directory.getCanonicalPath() + "\\src\\main\\java\\com\\Hello.xlass";
			path = Paths.get(xlassPath);
			bytes = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取.class 文件的二进制字节
    	for(int i =0; i<bytes.length; i++) {
    		bytes[i] = (byte) (255 - bytes[i]);
    	}
        //将二进制字节转化为Class对象
        clazz = defineClass(name,bytes,0,bytes.length);
        return clazz;
    }


	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
            
		HelloClassLoader helloClassLoader = new HelloClassLoader();
		Class<?> helloClass = helloClassLoader.findClass("Hello");
        Object obj = helloClass.newInstance();
        Method method = helloClass.getMethod("hello");
        method.invoke(obj);
        
	}
}
