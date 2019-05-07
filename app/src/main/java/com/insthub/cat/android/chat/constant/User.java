package com.insthub.cat.android.chat.constant;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;


import com.insthub.cat.android.App;
import com.insthub.cat.android.chat.utils.threaad.UserInfo;

import android.content.Intent;
import android.text.TextUtils;

/**
 * 用户管理类
 * 
 * @ClassName: User
 * @author yeliangliang
 * @date 2015-7-27 上午11:06:22
 */
public class User {

	private XMPPTCPConnectionConfiguration configuration = null;

	private AbstractXMPPConnection connection = null;
	// 用户信息
	private UserInfo userInfo = null;
	// 单例
	private static User user = null;
	// 聊天管理器
	private ChatManager chatManager = null;

	public static User getInstance() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	/**
	 * 获取XMPP配置器
	 * 
	 * @param passName
	 * @param passWord
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-27 上午11:41:50
	 * @version V1.0
	 * @return XMPPTCPConnectionConfiguration
	 */
	public XMPPTCPConnectionConfiguration getConfiguration(String passName, String passWord) {
		if (configuration == null) {
			configuration = XMPPTCPConnectionConfiguration.builder()
					.setUsernameAndPassword(passName, passWord)
					.setServiceName(Constant.SERVICE_NAME).setHost(Constant.IP)
					.setPort(Constant.LOGIN_PORT).setSecurityMode(SecurityMode.disabled)
					.setSendPresence(false).build();
		}
		return configuration;

	}

	/**
	 * 获取服务器连接器
	 * 
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-27 下午1:37:30
	 * @version V1.0
	 * @return AbstractXMPPConnection
	 */
	public AbstractXMPPConnection getConnection() {
		if (connection == null) {
			return null;
		}
		if (connection.isConnected()) {
			// 已连接服务器
			return connection;
		}
		return null;

	}

	/**
	 * 设置服务器连接器
	 * 
	 * @param c
	 *            连接配置
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-27 下午1:37:40
	 * @version V1.0
	 * @return AbstractXMPPConnection
	 */
	public AbstractXMPPConnection setConnection(XMPPTCPConnectionConfiguration c) {
		this.connection = new XMPPTCPConnection(c);
		/**
		 * 实现断线重连
		 */
		ReconnectionManager manager = ReconnectionManager.getInstanceFor(connection);
		manager.enableAutomaticReconnection();
		StanzaFilter filter = new StanzaFilter() {

			@Override
			public boolean accept(Stanza arg0) {
				return true;
			}
		};
		StanzaListener listenre = new StanzaListener() {

			@Override
			public void processPacket(Stanza arg0) throws NotConnectedException {
//				if (arg0 instanceof Presence) {
//					Presence presence = (Presence) arg0;
//					// Presence还有很多方法，可查看API
//					String to = presence.getFrom();// 接收方---一直是null
//					String from = presence.getTo();// 发送方---这里是个smack的bug，只能收到发送方.
//					// Presence.Type有7中状态
//					if (from == null || from == User.user.userInfo.getUserName()) {
//						return;
//					}
//					if (presence.getType().equals(Presence.Type.subscribe)) {
//						// 好友申请--直接默认同意
//						Presence presence2 = new Presence(Presence.Type.subscribed);
//						presence.setTo(from.split("@")[0]);
//						presence.setFrom(User.user.userInfo.getUserName());
//						connection.sendStanza(presence2);
//						// 发送广播--更新好友列表
//						Intent intent = new Intent(Constant.RECIVER_DELETE_FRIENDS);
//						App.mContext.sendBroadcast(intent, "com.wechat.permission.RECIVER_MESSAGE");
//					} else if (presence.getType().equals(Presence.Type.subscribed)) {// 同意添加好友
//
//					}
//				}
			}
		};
		connection.addPacketInterceptor(listenre, filter);
		return this.connection;

	}

	/**
	 * 设置用户信息
	 * 
	 * @param u
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-27 下午1:41:13
	 * @version V1.0
	 * @return UserInfo
	 */
	public UserInfo setUserInfo(UserInfo u) {
		this.userInfo = u;
		return this.userInfo;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-27 下午1:41:58
	 * @version V1.0
	 * @return UserInfo
	 */
	public UserInfo getUserInfo() {
		if (userInfo == null) {
			return null;
		}
		return this.userInfo;
	}

	/**
	 * 设置聊天管理器
	 * 
	 * @author yeliangliang
	 * @date 2015-8-3 下午6:20:45
	 * @version V1.0
	 * @return void
	 */
	public void setChatManager() {
		AbstractXMPPConnection connection = getConnection();
		if (connection == null) {
			return;
		}
		chatManager = ChatManager.getInstanceFor(connection);
		chatManager.addChatListener(new ChatManagerListener() {
			@Override
			public void chatCreated(Chat chat, boolean createdLocally) {
				if (!createdLocally)
					chat.addMessageListener(new ChatMessageListener() {

						@Override
						public void processMessage(Chat arg0, Message arg1) {
							// 发送广播
							Intent intent = new Intent(Constant.RECIVER_MESSAGE);
							// 获取来源和内容
							String from = arg0.getParticipant();
							String content = arg1.getBody();
							if (content != null && !TextUtils.isEmpty(content.trim())) {
								// 因为后台返回数据的问题，这里只能做一些切割
								String s[] = from.split("@");
								intent.putExtra("from", s[0] + "@" + Constant.SERVICE_NAME);
								intent.putExtra("body", content);
								App.mContext.sendOrderedBroadcast(intent,
										"com.wechat.permission.RECIVER_MESSAGE");
							}
						}
					});
			}
		});
	}

	/**
	 * }
	 * 
	 * /** 获取聊天管理器
	 * 
	 * @return
	 * @author yeliangliang
	 * @date 2015-8-3 下午6:21:53
	 * @version V1.0
	 * @return ChatManager
	 */
	public ChatManager getChatManager() {
		if (chatManager == null) {
			setChatManager();
		}
		return chatManager;

	}
}
