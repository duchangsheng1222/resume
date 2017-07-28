package com.resume.websocket;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class TestWebsocket {


	    private String deviceId;

	    private Session session;

	    public TestWebsocket () {
	        
	    }

	    public TestWebsocket (String deviceId) {
	        this.deviceId = deviceId;
	    }

	    protected boolean start() {
	        WebSocketContainer Container = ContainerProvider.getWebSocketContainer();
	        String uri = "ws://localhost:8080/security/websocket";
	        System.out.println("Connecting to " + uri);
	        try {
	            session = Container.connectToServer(TestWebsocket.class, URI.create(uri));
	            System.out.println("count: " + deviceId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }

	    public static void main(String[] args) {
	        for (int i = 1; i< 1000; i++) {
	        	TestWebsocket wSocketTest = new TestWebsocket(String.valueOf(i));
	            if (!wSocketTest.start()) {
	                System.out.println("测试结束！");
	                break;
	            }
	        }
	    }

}
