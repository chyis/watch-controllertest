package com.jdy.watch;


import com.jdy.watch.CommondLine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.*;

import com.jdy.collections.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import com.jdy.connecter.MysqlConnecter;
/**
 * 处理TextWebSocketFrame
 * 
 * @author waylau.com
 * 2015年3月26日
 */
public class TextWebSocketFrameHandler extends
		SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception { // (1)
		Channel incoming = ctx.channel();
		MysqlConnecter mysql = new MysqlConnecter();
		//for (Channel channel : channels) {
        //    if (channel != incoming){
        //        channel.writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.text()));
        //    } else {
        //    	channel.writeAndFlush(new TextWebSocketFrame("[you]" + msg.text() ));
        //    }
        //}
		String txt = msg.text();
		String commodName = "";
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println(df.format(day));
		if(JsonUtils.isJsonString(txt)){
			System.out.println("json decode ok");
			CommondLine cmd = JsonUtils.fromJson(txt,CommondLine.class );
			String merchantid = cmd.getmerchantid();
			String deviceid = cmd.getdeviceid();
			String cmdcode = cmd.toCommond();
			String sql = "";
			
			if(!cmdcode.equals("")) {
				sql = "insert into watch_commonds (`merchantid`,`deviceid`,`commond`,`status`) values('"+merchantid+"','"+deviceid+"','"+cmdcode+"','0' )";
			}
			if(!sql.equals("")){
				mysql.insert(sql);
			}
			
			ctx.writeAndFlush(new TextWebSocketFrame("[SERVER] - "+df.format(day)+" :"+cmdcode));
		}else{
			String sql = "select * from watch_log where status=0";
			Map<String,Object> lod = mysql.find(sql);
			if(lod!=null){
				String res = lod.get("logdata").toString();
				ctx.writeAndFlush(new TextWebSocketFrame("[client] - "+df.format(day)+" :"+res));
				sql = "update watch_log set status=1 where logid="+lod.get("logid").toString();
				mysql.update(sql);
			}else{
				//String mid = ((ServletServerHttpRequest) req).getServletRequest().getParameter("mid");
				
				//ctx.writeAndFlush(new TextWebSocketFrame("[client] - "+df.format(day)+" :"+mid));
			}
		}
		mysql.release();
	}
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        //Channel incoming = ctx.channel();
        
        // Broadcast a message to multiple Channels
        //channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入"));
        
        //channels.add(incoming);
		//System.out.println("Client:"+incoming.remoteAddress() +"加入");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        
        // Broadcast a message to multiple Channels
        //channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));
        
		//System.out.println("Client:"+incoming.remoteAddress() +"离开");

        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
    }
	    
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        //Channel incoming = ctx.channel();
		//System.out.println("Client:"+incoming.remoteAddress()+"在线");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        //Channel incoming = ctx.channel();
		//System.out.println("Client:"+incoming.remoteAddress()+"掉线");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)	// (7)
			throws Exception {
    	//Channel incoming = ctx.channel();
		//System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
	}

}
