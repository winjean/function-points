package com.winjean.design_pattern.creational.abstract_factory;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/23 14:02
 * 修改人：Administrator
 * 修改时间：2018/10/23 14:02
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class ConcreteProductA1 implements IProductA {
    @Override
    public void getInfo() {
        System.out.println("this is concrete product A 1!");
    }
}
