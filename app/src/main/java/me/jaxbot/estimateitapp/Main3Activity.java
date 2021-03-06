package me.jaxbot.estimateitapp;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wlcp.gameserver.api.IWLCPGameClient;
import wlcp.gameserver.api.WLCPGameClient;
import wlcp.gameserver.api.exception.WLCPGameServerCouldNotConnectException;

public class Main3Activity extends AppCompatActivity {
    private EditText editText;
    private TextView displayTextView; //this is the display section
    private float sizeFont = 0;
    private Button redbtn;
    private Button bluebtn;
    private Button greenbtn;
    private Button blackbtn;

    private Button singleOneBtn;
    private Button singleTwoBtn;
    private Button singleThreeBtn;
    private Button singleFourBtn;

    private Button Disconnect;
    private Button Clear;
    private Button Submit;
    private LinearLayout fourButtons;
    private LinearLayout Sequence;
    private LinearLayout singleButton;
    private int length;
    private static final String TAG = "Logging Example";
    private ArrayList list;
    public Boolean btn1Colored;
    public Boolean btn2Colored;
    public Boolean btn3Colored;
    public Boolean btn4Colored;

    private Button bbtn1;
    private Button bbtn2;
    private Button bbtn3;
    private Button bbtn4;

    public String inputType;
    private TextView textInput;

    private TextView displayText;
    private String username;
    private String gamePin;
    //private StringBuilder colorSequence;

    private IWLCPGameClient gameClient;


    //private static final string[] colorArray;

   // private LinearLayout targetArea;

    private void showError() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Sequence.startAnimation(shake);
        Toast.makeText(Main3Activity.this, "That was the wrong sequence, try again", Toast.LENGTH_SHORT).show();
    }

    StringBuilder colorSequence = new StringBuilder();
    public void setColor(String btnColor, ArrayList list, String colorCode){
        list.add(btnColor);
        colorSequence.append(colorCode);
        if(!btn1Colored){
            bbtn1.setBackgroundColor(Color.parseColor(btnColor));
            btn1Colored = true;
            bbtn1.setVisibility(View.VISIBLE);
        } else if (!btn2Colored){
            bbtn2.setBackgroundColor(Color.parseColor(btnColor));
            btn2Colored = true;
            bbtn2.setVisibility(View.VISIBLE);
        } else if (!btn3Colored){
            bbtn3.setBackgroundColor(Color.parseColor(btnColor));
            btn3Colored = true;
            bbtn3.setVisibility(View.VISIBLE);
        } else if (!btn4Colored){
            bbtn4.setBackgroundColor(Color.parseColor(btnColor));
            btn4Colored = true;
            bbtn4.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String sPlayer = getIntent().getStringExtra("team");
        String sTeam = getIntent().getStringExtra("player");

        int selectedTeam = Integer.valueOf(sTeam) - 1;
        int selectedPlayer = Integer.valueOf(sPlayer) - 1;


        username = getIntent().getStringExtra("username");
        gamePin = getIntent().getStringExtra("gamePin");
        int gamePinInt = Integer.valueOf(gamePin);

        displayTextView = this.findViewById(R.id.displayText);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameClient = new WLCPGameClient("130.215.45.83", 3333, gamePinInt, username);
                try {
                    gameClient.connect(selectedTeam, selectedPlayer, new WLCPGameServerSessionHandlerImpl(gameClient, Main3Activity.this));


                } catch (WLCPGameServerCouldNotConnectException e) {
                    e.printStackTrace();
                } // this should be in the next screen where they select their players and teams
            }
        });
        thread.start();



        textInput = findViewById(R.id.textInput);
        fourButtons = findViewById(R.id.fourButtons);
        singleButton = findViewById(R.id.singleButton);


        //Setting the right type of font
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/simple.ttf");


        // if the input type is a sequence of buttons
        list = new ArrayList<String>();
        btn1Colored = false;
        btn2Colored = false;
        btn3Colored = false;
        btn4Colored = false;

        bbtn1 = findViewById(R.id.sequence1);
        bbtn2 = findViewById(R.id.sequence2);
        bbtn3 = findViewById(R.id.sequence3);
        bbtn4 = findViewById(R.id.sequence4);

        bbtn1.setVisibility(View.GONE);
        bbtn2.setVisibility(View.GONE);
        bbtn3.setVisibility(View.GONE);
        bbtn4.setVisibility(View.GONE);


         redbtn = this.findViewById(R.id.redbtn);
         bluebtn = this.findViewById(R.id.bluebtn);
         greenbtn = this.findViewById(R.id.greenbtn);
         blackbtn = this.findViewById(R.id.blackbtn);



        displayTextView.setTypeface(myCustomFont);
        redbtn.setTypeface(myCustomFont);
        bluebtn.setTypeface(myCustomFont);
        greenbtn.setTypeface(myCustomFont);
        blackbtn.setTypeface(myCustomFont);


        Disconnect = findViewById(R.id.disconnect);
        Disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    gameClient.disconnectFromGameInstance();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //disconnect();
                Intent intent = new Intent(Main3Activity.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        Clear = findViewById(R.id.clear);
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSequence();

            }
        });

        Submit = findViewById(R.id.submit);
        Sequence = findViewById(R.id.sequence);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorSequence.length() == 0){// This is just for presentation purposes. In reality there will be a check to see if the sequence is accurate, not if it s
                    showError();
                }
                try {
                    gameClient.sendSequenceButtonPress(colorSequence.toString());
                    clearSequence();


                } catch (Exception e) {
                    e.printStackTrace();
                    showError();
                }

            }
        });

        redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("red", list, "1");
            }
        });

        bluebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("blue", list, "3");
            }
        });

        greenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("#009900", list, "2"); //Green color
            }
        });

        blackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("black", list, "4");
            }
        });

        singleOneBtn = findViewById(R.id.single1); // Red
        singleTwoBtn = findViewById(R.id.single2); //Green
        singleThreeBtn = findViewById(R.id.single3); //Blue
        singleFourBtn = findViewById(R.id.single4); //Black

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                singleOneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            gameClient.sendSingleButtonPress(1);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

                singleTwoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            gameClient.sendSingleButtonPress(2);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

                singleThreeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            gameClient.sendSingleButtonPress(3);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

                singleFourBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            gameClient.sendSingleButtonPress(4);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        });

    }

//    private void disconnect () {
//        //Log.d("STATE", gameLobby);
//        Intent intent = new Intent(Main3Activity.this, LoginScreen.class);
//        startActivity(intent);
//
//    }

   public void setInputVisibility(String inputType) {
        if(inputType.equals("sequence")){
            textInput.setVisibility(View.GONE);
            singleButton.setVisibility(View.GONE);
            fourButtons.setVisibility(View.VISIBLE);
        }

        if(inputType.equals("singleButtonPress")){
            textInput.setVisibility(View.GONE);
            singleButton.setVisibility(View.VISIBLE);
            fourButtons.setVisibility(View.GONE);
        }

        if(inputType.equals("textInput")) {
            textInput.setVisibility(View.VISIBLE);
            singleButton.setVisibility(View.GONE);
            fourButtons.setVisibility(View.GONE);

        }
    }


    public void refreshText(int stringLength){
        if(stringLength > 180){
            displayTextView.setTextSize(25);
        }
        if(stringLength >= 150 && stringLength <= 180){
            displayTextView.setTextSize(30);
        }
        if(stringLength >= 120 && stringLength < 150){
            displayTextView.setTextSize(35);
        }
        if(stringLength < 120){
            displayTextView.setTextSize(40);
        }
    }

    public void getDisplayText(String text) {
        int stringLength = text.length();
        displayTextView.setText(text);
        runOnUiThread(new Runnable() {
            public void run() {
                refreshText(stringLength);
            }
        });
    }

    public void setSingleBtnDisplay() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setInputVisibility("singleButtonPress");
            }
        });
    }

    public void setBtnSequenceDisplay() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setInputVisibility("sequence");
            }
        });
    }

    public void setTextInputDisplay() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setInputVisibility("textInput");
            }
        });

    }

    public void clearSequence() {
        btn1Colored = false;
        btn2Colored = false;
        btn3Colored = false;
        btn4Colored = false;

        bbtn1.setBackgroundColor(Color.LTGRAY);
        bbtn2.setBackgroundColor(Color.LTGRAY);
        bbtn3.setBackgroundColor(Color.LTGRAY);
        bbtn4.setBackgroundColor(Color.LTGRAY);

        bbtn1.setVisibility(View.GONE);
        bbtn2.setVisibility(View.GONE);
        bbtn3.setVisibility(View.GONE);
        bbtn4.setVisibility(View.GONE);

        list.clear();
        colorSequence.setLength(0);
    }

}
