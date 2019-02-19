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

public class Main3Activity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private float sizeFont = 0;
    private Button redbtn;
    private Button bluebtn;
    private Button greenbtn;
    private Button blackbtn;
    private Button Disconnect;
    private Button Clear;
    private Button Submit;
    private LinearLayout fourButtons;
    private LinearLayout Sequence;
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


    //private static final string[] colorArray;

   // private LinearLayout targetArea;

    private void showError() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Sequence.startAnimation(shake);
        Toast.makeText(Main3Activity.this, "That was the wrong sequence, try again", Toast.LENGTH_SHORT).show();
    }

    public void setColor(String btnColor, ArrayList list){
        list.add(btnColor);
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

        inputType = "sequence";
       // inputType = "singleButtonPress";
       // inputType = "textInput";

        textInput = findViewById(R.id.textInput);
        fourButtons = findViewById(R.id.fourButtons);

        //Check what kind of input this requires

        if(inputType == "sequence" || inputType == "singleButtonPress"){
            textInput.setVisibility(View.GONE);
            fourButtons.setVisibility(View.VISIBLE);
        }

        if(inputType == "textInput") {
            textInput.setVisibility(View.VISIBLE);
            fourButtons.setVisibility(View.GONE);
        }

        //Setting the right type of font
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/simple.ttf");

        //length = textView.length();
        //textView.length();
        //String convert = String.valueOf(length);
        //Log.d("STATE", convert);

        // if the input type is a sequence of buttons
        list = new ArrayList<String>();
        btn1Colored = false;
        btn2Colored = false;
        btn3Colored = false;
        btn4Colored = false;

        bbtn1 = findViewById(R.id.button2);
        bbtn2 = findViewById(R.id.button3);
        bbtn3 = findViewById(R.id.button4);
        bbtn4 = findViewById(R.id.button5);

        bbtn1.setVisibility(View.GONE);
        bbtn2.setVisibility(View.GONE);
        bbtn3.setVisibility(View.GONE);
        bbtn4.setVisibility(View.GONE);

        textView = this.findViewById(R.id.textView);
        textView.measure(0,0);
        textView.getMeasuredHeight();
        length = textView.getMeasuredWidth();
        //textView.getText()
        textView.setTextSize(30);
        //textView.length();

         redbtn = this.findViewById(R.id.redbtn);
         bluebtn = this.findViewById(R.id.bluebtn);
         greenbtn = this.findViewById(R.id.greenbtn);
         blackbtn = this.findViewById(R.id.blackbtn);



        textView.setTypeface(myCustomFont);
        redbtn.setTypeface(myCustomFont);
        bluebtn.setTypeface(myCustomFont);
        greenbtn.setTypeface(myCustomFont);
        blackbtn.setTypeface(myCustomFont);


        Disconnect = findViewById(R.id.disconnect);
        Disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disconnect();
            }
        });

        Clear = findViewById(R.id.clear);
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        Submit = findViewById(R.id.submit);
        Sequence = findViewById(R.id.sequence);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!list.isEmpty()){ // This is just for presentation purposes. In reality there will be a check to see if the sequence is accurate, not if it s
                    showError();
                }
            }
        });

        redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("red", list);
            }
        });

        bluebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("blue", list);
            }
        });

        greenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("green", list);
            }
        });

        blackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor("black", list);
            }
        });




        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textView.setText(charSequence);

                textView.setTextSize(60);
                //textView.setTextSize(sizeFont+30);
                //textView.get;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void disconnect () {
        //Log.d("STATE", gameLobby);
        Intent intent = new Intent(Main3Activity.this, LoginScreen.class);
        startActivity(intent);
    }

   /* private void clear () {
        targetArea.removeAllViews();
    }*/
}
