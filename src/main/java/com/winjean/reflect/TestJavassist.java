package com.winjean.reflect;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.reflect.Method;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/24 17:26
 * 修改人：Administrator
 * 修改时间：2018/10/24 17:26
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class TestJavassist {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("public String getString(String arg){");
        sb.append("  System.out.println(arg);");
        sb.append("  return arg;");
        sb.append("}");

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("TestJavassist");
        CtMethod method = CtNewMethod.make(sb.toString(), cc);
        cc.addMethod(method);

        Class clazz =cc.toClass();
        Method method1 = clazz.getMethod("getString",String.class);
        Object str = method1.invoke(clazz.newInstance(),"hoho");
        System.out.println(str);

        CtClass test = pool.get("com.winjean.reflect.Test");

        CtMethod m = CtNewMethod.make(sb.toString(), test);
        test.addMethod(m);

        Class cls = test.toClass();
        Method method3 = cls.getMethod("method1",String.class, Integer.class);
        Object object =  cls.newInstance();
        Object result = method3.invoke(object,"winjean",3);
        System.out.println(result);

        Method[] methods = Test.class.getDeclaredMethods();
        for (Method mth : methods){
            mth.setAccessible(true);
            System.out.println("\n===============" + mth.getName() + "====================");
        }
    }
}
