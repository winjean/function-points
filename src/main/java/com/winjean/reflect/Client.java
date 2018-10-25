package com.winjean.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/24 11:31
 * 修改人：Administrator
 * 修改时间：2018/10/24 11:31
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class Client {

    //javassist & ASM
    public static void main(String[] args) {
        Test test = new Test(1,"test");

        //获取field信息
        Field[] fields = Test.class.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            try {
                System.out.println("field name : " +field.getName() + ", type : " + field.getType().getSimpleName() + ", value : " + field.get(test)); ;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //获取Method信息
        Method[] methods = Test.class.getDeclaredMethods();

        for(Method method : methods){
            method.setAccessible(true);
            System.out.println("\n===============" + method.getName() + "====================");
            try {
                Parameter[] parameters = method.getParameters();
                int count = method.getParameterCount();
                System.out.println("ParameterCount : " + count);
                for(Parameter parameter : parameters){
                    System.out.println("parameter name : " + parameter.getName() + ", type : " + parameter.getType().getSimpleName());
                }
                Object object = method.invoke(test,"arg1",3);
                System.out.println( "return value : "+ String.valueOf(object));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }



//        Method add_method =

    }
}
