package org.andrew.commons.websocket.nettyhttp;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.andrew.commons.websocket.nettyhttp.model.PongVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(
        WebSocketServerHandshaker.class.getName());

    private WebSocketServerHandshaker handShaker;

    // websocket 服务的 uri
    private static final String WEB_SOCKET_PATH = "/websocket";

    // 本次请求的唯一key值参数
    private static final String HTTP_REQUEST_KEY = "key";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端与WebSocket服务端连接开启");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 移除
        NettyChannelCenter.remove(ctx.channel());
        ctx.close();
        logger.info("客户端与WebSocket服务端连接关闭");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handShaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 判断是否文本消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(
                String.format("%s frame types not supported", frame.getClass().getName()));
        }
        //服务器拿到文本消息
        String request = ((TextWebSocketFrame) frame).text();
        logger.info("WebSocket服务端收到：{}", request);

        //如果接收到客户端心跳消息ping，则返回99
        if ("ping".equals(request)) {
            PongVo pongVo = new PongVo();
            logger.info("WebSocket服务端发送：{}", JSON.toJSONString(pongVo));
            TextWebSocketFrame tws = new TextWebSocketFrame(JSON.toJSONString(pongVo));
            Channel channel = ctx.channel();
            if (channel != null && channel.isActive()) {
                channel.writeAndFlush(tws);
            }
        }

        //TextWebSocketFrame tws = new TextWebSocketFrame("成功了");
        //根据类型和key拿到对应的channelId
        //Channel channel = NettyChannelCenter.get("1");
        //if (channel != null && channel.isActive()) {
        //    channel.writeAndFlush(tws);
        //}
    }

    /**
     * websocket客户端与服务器握手。
     *
     * @param ctx ChannelHandlerContext
     * @param req FullHttpRequest
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // Handle a bad request.
        if (!req.getDecoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                   HttpResponseStatus.BAD_REQUEST));
            return;
        }

        // Allow only GET or POST methods.
        if (req.getMethod() != HttpMethod.GET && req.getMethod() != HttpMethod.POST) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                   HttpResponseStatus.FORBIDDEN));
            return;
        }

        if ("/favicon.ico".equals(req.getUri()) || ("/".equals(req.getUri()))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                   HttpResponseStatus.NOT_FOUND));
            return;
        }

        //获取唯一key值
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.getUri());
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        if (parameters.size() == 0 || !parameters.containsKey(HTTP_REQUEST_KEY)) {
            logger.error("WebSocket客户端{} 参数不可缺省", HTTP_REQUEST_KEY);
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                   HttpResponseStatus.NOT_FOUND));
            return;
        }
        String key = parameters.get(HTTP_REQUEST_KEY).get(0);
        // Handshake
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
            getWebSocketLocation(req), null, false);
        handShaker = wsFactory.newHandshaker(req);
        if (handShaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            ChannelFuture channelFuture = handShaker.handshake(ctx.channel(), req);
            // 握手成功之后,业务逻辑
            if (channelFuture.isSuccess()) {
                logger.info("客户端与WebSocket服务器端连接成功");
                //将此channel放入内存中
                try {
                    NettyChannelCenter.put(key, ctx.channel());
                } catch (Exception ex) {
                    logger.error("webSocket通道放入内存失败{}", ex);
                }
                logger.debug("连接完成put key:{}", key);
            }
        }
    }

    /**
     * 拼接websocket链接地址。
     *
     * @param req 请求
     * @return websocket地址
     */
    private static String getWebSocketLocation(FullHttpRequest req) {

        String location = req.headers().get("host") + WEB_SOCKET_PATH;
        return "ws://" + location;
    }

    /**
     * 发送响应。
     *
     * @param ctx ChannelHandlerContext
     * @param req FullHttpRequest
     * @param res DefaultFullHttpResponse
     */
    private static void sendHttpResponse(
        ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static boolean isKeepAlive(FullHttpRequest req) {
        return false;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("WebSocket服务端异常：{}", cause);
        //移除Channel
        NettyChannelCenter.remove(ctx.channel());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
        } else if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }
}