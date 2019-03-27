package me.jaxbot.estimateitapp;

import android.app.Activity;

import wlcp.gameserver.api.WLCPGameClientSessionHandler;

import wlcp.gameserver.api.IWLCPGameClient;
import wlcp.shared.message.*;

/**
 * Created by estheragbaji on 2/20/19.
 */

public class WLCPGameServerSessionHandlerImpl extends WLCPGameClientSessionHandler {

    public WLCPGameServerSessionHandlerImpl(IWLCPGameClient gameClient, Object activity) {
        super(gameClient, activity);
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

        System.out.println("this is the display text, I need to send back " + msg.displayText);
        ((Main3Activity) activity).getDisplayText(msg.displayText);
    }

    @Override
    public void singleButtonPressRequest() {

        System.out.print("Please Enter a button 1-4: ");
        ((Main3Activity) activity).setSingleBtnDisplay();
    }

    @Override
    public void sequenceButtonPressRequest() {
        System.out.print("Please Enter a sequence using the numbers 1-4: ");
        ((Main3Activity) activity).setBtnSequenceDisplay();
    }

    @Override
    public void keyboardInputRequest() {
        System.out.print("Please Enter some keyboard input: ");
        ((Main3Activity) activity).setTextInputDisplay();
    }

}
