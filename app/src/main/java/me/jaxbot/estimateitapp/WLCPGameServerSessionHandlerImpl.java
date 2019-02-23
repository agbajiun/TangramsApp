package me.jaxbot.estimateitapp;

import wlcp.gameserver.api.WLCPGameClientSessionHandler;

import wlcp.gameserver.api.IWLCPGameClient;
import wlcp.shared.message.*;

/**
 * Created by estheragbaji on 2/20/19.
 */

public class WLCPGameServerSessionHandlerImpl extends WLCPGameClientSessionHandler {

    public WLCPGameServerSessionHandlerImpl(IWLCPGameClient gameClient) {
        super(gameClient);
    }

    @Override
    public void connectedToServer() {
        System.out.println("Connected To Game Server!");
    }

    @Override
    public void connectedToGameInstance() {
        System.out.println("Connected To Game Instance!");
    }

    @Override
    public void disconnectedFromServer() {
        System.out.println("Disconnected From Game Server!");
    }

    @Override
    public void disconnectedFromGameInstance() { System.out.println("Disconnected From Game Instance!"); }

    @Override
    public void displayTextRequest(DisplayTextMessage msg) {
        System.out.println(msg.displayText);
    }

    @Override
    public void singleButtonPressRequest() {
        System.out.print("Please Enter a button 1-4: ");
    }

    @Override
    public void sequenceButtonPressRequest() {
        System.out.print("Please Enter a sequence using the numbers 1-4: ");
    }

    @Override
    public void keyboardInputRequest() {
        System.out.print("Please Enter some keyboard input: ");
    }
}
