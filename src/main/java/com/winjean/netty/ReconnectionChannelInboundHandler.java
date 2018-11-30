package com.winjean.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/29 10:24
 * 修改人：winjean
 * 修改时间：2018/11/29 10:24
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
@Data
public class ReconnectionChannelInboundHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connection failure");
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> NettyClient.connect(new Bootstrap(),eventLoop),5, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
