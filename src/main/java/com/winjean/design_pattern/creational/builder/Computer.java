package com.winjean.design_pattern.creational.builder;

import lombok.Data;
import lombok.ToString;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/23 15:59
 * 修改人：Administrator
 * 修改时间：2018/10/23 15:59
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
@Data
public class Computer {
    public String master;
    public String screen;
    public String keyboard;
    public String mouse;
    public String audio;

    @Override
    public String toString(){
        return "computer info : " + master + "-" +screen + "-" +keyboard + "-" +mouse + "-" +audio;
    }
}
