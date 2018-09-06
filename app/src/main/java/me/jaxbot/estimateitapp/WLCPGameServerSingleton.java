package me.jaxbot.estimateitapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.nio.channels.CompletionHandler;

import wlcp.gameserver.api.IWLCPGameServer;
import wlcp.gameserver.api.WLCPBaseGameServerListener;
import wlcp.gameserver.api.WLCPGameServer;
import wlcp.gameserver.api.WLCPGameServerFactory;
import wlcp.gameserver.api.WLCPGameServerListener;

/**
 * Created by estheragbaji on 8/31/18.
 */

public class WLCPGameServerSingleton {

    private static WLCPGameServerSingleton instance = null;
    private static IWLCPGameServer wlcpGameServer = null;
    private static WLCPGameServerListenerImpl wlcpGameServerListenerImpl = null;

    private WLCPGameServerSingleton() { }

    public static WLCPGameServerSingleton getInstance() {
        if(instance == null) {
            instance = new WLCPGameServerSingleton();
        }
        return instance;
    }

    public IWLCPGameServer getGameServer() {
        return wlcpGameServer;
    }

    @TargetApi(26)
    public static <A> void connectToServer(final CompletionHandler<Void, ? super A> completionHandler, final A attachment) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //Create the game server container
                wlcpGameServer = WLCPGameServerFactory.createServer("130.215.45.83", 3333);

                //Create an event listener class
                wlcpGameServerListenerImpl = new WLCPGameServerListenerImpl();

                //Register the event listener
                wlcpGameServer.registerEventListener(wlcpGameServerListenerImpl);

                //Make the TCP connection to the server
                wlcpGameServer.connect(new CompletionHandler<Void, WLCPGameServer>() {
                    @Override
                    public void completed(Void result, WLCPGameServer server) {
                        Log.d("WLCP Game Server", "Connected!");
                        completionHandler.completed(null, attachment);
                    }
                    @Override
                    public void failed(Throwable exc, WLCPGameServer server) {
                        Log.d("WLCP Game Server", "Failed to connect " + exc.getMessage());
                        completionHandler.failed(null, attachment);
                    }
                }, (WLCPGameServer) wlcpGameServer);
            }
        });
        thread.start();
    }

    @TargetApi(26)
    public static <A> void disconnectFromServer(final CompletionHandler<Void, ? super A> completionHandler, final A attachment) {
        //TODO wlcpGameServer.disconnect
    }
}

class WLCPGameServerListenerImpl extends WLCPBaseGameServerListener implements WLCPGameServerListener {

}
