package com.winjean.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/31 18:03
 * 修改人：Administrator
 * 修改时间：2018/10/31 18:03
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */

//TimeServerHandler继承自ChannelHandlerAdapter，它用于对网络事件进行读写操作
//通常我们只需要关注channelRead和exceptionCaught方法。
public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //做类型转换，将msg转换成Netty的ByteBuf对象。
        //ByteBuf类似于JDK中的java.nio.ByteBuffer 对象，不过它提供了更加强大和灵活的功能。
        ByteBuf buf = (ByteBuf) msg;
        //通过ByteBuf的readableBytes方法可以获取缓冲区可读的字节数，
        //根据可读的字节数创建byte数组
        byte[] req = new byte[buf.readableBytes()];
        //通过ByteBuf的readBytes方法将缓冲区中的字节数组复制到新建的byte数组中
        buf.readBytes(req);
        //通过new String构造函数获取请求消息。
        String body = new String(req, "UTF-8");
        System.out.println("The time server receive order : " + body);
        //如果是"QUERY TIME ORDER"则创建应答消息，
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
                System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //通过ChannelHandlerContext的write方法异步发送应答消息给客户端。
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //调用了ChannelHandlerContext的flush方法，它的作用是将消息发送队列中的消息写入到SocketChannel中发送给对方。
        //从性能角度考虑，为了防止频繁地唤醒Selector进行消息发送，
        //Netty的write方法并不直接将消息写入SocketChannel中，调用write方法只是把待发送的消息放到发送缓冲数组中，
        //再通过调用flush方法，将发送缓冲区中的消息全部写到SocketChannel中。
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
