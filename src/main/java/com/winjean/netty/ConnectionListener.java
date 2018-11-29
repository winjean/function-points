package com.winjean.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/29 16:06
 * 修改人：winjean
 * 修改时间：2018/11/29 16:06
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class ConnectionListener implements ChannelFutureListener{

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if( !future.isSuccess()){
            System.out.println("reconnect ....");
            final EventLoop loop = future.channel().eventLoop();
            loop.schedule( () -> TimeClient.connect(new Bootstrap(), loop), 5, TimeUnit.SECONDS);
        }
    }
}
