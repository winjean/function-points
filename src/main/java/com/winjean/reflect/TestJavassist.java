package com.winjean.reflect;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

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

        ClassPool pool = ClassPool.getDefault();

        //生成类
        CtClass cc = pool.makeClass("TestJavassist");

        // 创建方法
        CtClass ccStringType = pool.get("java.lang.String");
        // 参数：  1：返回类型  2：方法名称  3：传入参数类型  4：所属类CtClass
        CtMethod ctMethod=new CtMethod(ccStringType,"sayHello",new CtClass[]{ccStringType},cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        StringBuffer body=new StringBuffer();
        body.append("{");
        body.append("\n    System.out.println($1);");
        body.append("\n    return \"Hello, \" + $1;");
        body.append("\n}");
        ctMethod.setBody(body.toString());
        cc.addMethod(ctMethod);

        ClassFile ccFile = cc.getClassFile();
        ConstPool constPool = ccFile.getConstPool();

        // 添加类注解
        AnnotationsAttribute bodyAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation bodyAnnot = new Annotation("javax.jws.WebService", constPool);
        bodyAnnot.addMemberValue("name", new StringMemberValue("HelloWoldService", constPool));
        bodyAttr.addAnnotation(bodyAnnot);

        ccFile.addAttribute(bodyAttr);

        // 添加方法注解
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation methodAnnot = new Annotation("javax.jws.WebMethod", constPool);
        methodAnnot.addMemberValue("operationName", new StringMemberValue("sayHelloWorld", constPool));
        methodAttr.addAnnotation(methodAnnot);

        Annotation resultAnnot = new Annotation("javax.jws.WebResult", constPool);
        resultAnnot.addMemberValue("name", new StringMemberValue("result", constPool));
        methodAttr.addAnnotation(resultAnnot);

        //value 值为数组
        Annotation requestMappingAnnotation = new Annotation("org.springframework.web.bind.annotation.RequestMapping", constPool);
        ArrayMemberValue classValues = new ArrayMemberValue(constPool);
        MemberValue[] classMemberValue = {new StringMemberValue("org.springframework.web.bind.annotation.RequestMapping", constPool)};
        classValues.setValue(classMemberValue);
        requestMappingAnnotation.addMemberValue("value", classValues);
        bodyAttr.addAnnotation(requestMappingAnnotation);

        ctMethod.getMethodInfo().addAttribute(methodAttr);

        // 添加参数注解
        ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(
                constPool, ParameterAnnotationsAttribute.visibleTag);
        Annotation paramAnnot = new Annotation("javax.jws.WebParam", constPool);
        paramAnnot.addMemberValue("name", new StringMemberValue("name",constPool));
        Annotation[][] paramArrays = new Annotation[1][1];
        paramArrays[0][0] = paramAnnot;
        parameterAtrribute.setAnnotations(paramArrays);

        ctMethod.getMethodInfo().addAttribute(parameterAtrribute);

        //将类输出
        cc.writeFile();
    }
}
