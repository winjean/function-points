package com.winjean.design_pattern.creational.builder;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/23 16:06
 * 修改人：Administrator
 * 修改时间：2018/10/23 16:06
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Director director = new Director();
        ComputerBuilder hp = new HPComputerBuilder();
        director.setComputerBuilder(hp);
        director.constructComputer();

        Computer pc = director.getComputer();
        System.out.println(pc);
    }
}
