package com.winjean.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/31 18:02
 * 修改人：Administrator
 * 修改时间：2018/10/31 18:02
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class NettyServer {
    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组
        //NioEventLoopGroup是个线程组，它包含了一组NIO线程，专门用于网络事件的处理，
        //实际上它们就是Reactor线程组。
        //这里创建两个的原因是一个用于服务端接受客户端的连接，另一个用于进行SocketChannel的网络读写。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap对象，它是Netty用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度。
            ServerBootstrap b = new ServerBootstrap();
            //调用ServerBootstrap的group方法，将两个NIO线程组当作入参传递到ServerBootstrap中。
            //接着设置创建的Channel为NioServerSocketChannel，它的功能对应于JDK NIO类库中的ServerSocketChannel类。
            //然后配置NioServerSocketChannel的TCP参数，此处将它的backlog设置为1024，
            //最后绑定I/O事件的处理类ChildChannelHandler，它的作用类似于Reactor模式中的handler类，
            //主要用于处理网络I/O事件，例如记录日志、对消息进行编解码等。
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待成功
            //服务端启动辅助类配置完成之后，调用它的bind方法绑定监听端口
            //随后，调用它的同步阻塞方法sync等待绑定操作完成。
            //完成之后Netty会返回一个ChannelFuture，它的功能类似于JDK的java.util.concurrent.Future，主要用于异步操作的通知回调。
            ChannelFuture f = b.bind(port).sync();

            //等待服务端监听端口关闭
            //使用f.channel().closeFuture().sync()方法进行阻塞,等待服务端链路关闭之后main函数才退出。
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            //调用NIO线程组的shutdownGracefully进行优雅退出，它会释放跟shutdownGracefully相关联的资源。
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
//            channel.pipeline().addLast(new TimeServerHandler());
            //将字节解码为HttpRequest、HttpContent 和LastHttpContent。并将HttpRequest、HttpContent 和LastHttpContent 编码为字节
//            channel.pipeline().addLast(new HttpServerCodec());
            //HTTP请求消息解码器
            channel.pipeline().addLast("http-request-decoder", new HttpRequestDecoder());
            //添加HttpObjectAggregator解密器，其作用是将多个消息转换为单一的FullHttpRequest或者FullHttpResponse，
            //原因是HTTP解码器在每个HTTP消息中会生成多个消息对象：有1、HttpRequest/HttpResponse;2、HttpContent；3、LastHttpContent；
            channel.pipeline().addLast("http-object-aggregator", new HttpObjectAggregator(65536));
            //HTTP响应编码器
            channel.pipeline().addLast("http-response-encoder", new HttpResponseEncoder());
            //支持异步发送大的码流（例如大的文件传输），但不占用过多的内存，防止发生JAVA内存溢出错误
            channel.pipeline().addLast("chunked-write-handler", new ChunkedWriteHandler());

            channel.pipeline().addLast(new HttpServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind(8080);
    }
}
