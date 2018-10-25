package com.winjean.reflect;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/24 11:29
 * 修改人：Administrator
 * 修改时间：2018/10/24 11:29
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class Test {
    private int field1;

    private String field2;

    public Test(){
        super();
    }

    public Test(int arg1,String arg2){
        super();
        this.field1 = arg1;
        this.field2 = arg2;
    }

    public String method1(String arg1,Integer arg2){
        System.out.println("method1 invoked !");
        return "method1";
    }

    private String method2(String arg1,int arg2){
        System.out.println("method2 invoked !");
        return "method2";
    }
}
