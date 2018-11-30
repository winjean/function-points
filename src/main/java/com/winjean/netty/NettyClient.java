package com.winjean.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/31 18:05
 * 修改人：Administrator
 * 修改时间：2018/10/31 18:05
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class NettyClient {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8080;

    public static void connect(Bootstrap bootstrap, EventLoopGroup group) {
        try {
            //与服务端不同的是，它的Channel需要设置为NioSocketChannel
            //然后为其添加handler，此处为了简单直接创建匿名内部类，实现initChannel方法，
            //其作用是当创建NioSocketChannel成功之后，
            //在初始化它的时候将它的ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件。
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new TimeClientHandler())
                            .addLast(new ReconnectionChannelInboundHandler());
                        }
                    });
            // 发起异步连接操作
            //客户端启动辅助类设置完成之后，调用connect方法发起异步连接，
            //然后调用同步方法等待连接成功。
            try {
                ChannelFuture f = bootstrap.connect(HOST, PORT).addListener(new ConnectionListener());
                f.sync();
                // 等待客户端链路关闭
                //当客户端连接关闭之后，客户端主函数退出.
                f.channel().closeFuture().sync();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            // 优雅退出，释放NIO线程组
            //在退出之前，释放NIO线程组的资源。
//            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        //继续创建客户端辅助启动类Bootstrap，随后需要对其进行配置。
        Bootstrap bootstrap = new Bootstrap();

        // 配置客户端NIO线程组
        //首先创建客户端处理I/O读写的NioEventLoopGroup线程组
        EventLoopGroup group = new NioEventLoopGroup();

        NettyClient.connect(bootstrap, group);
    }
}
