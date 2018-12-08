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


    //private static final string[] colorArray;

    private LinearLayout targetArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/simple.ttf");

        //length = textView.length();
        //textView.length();
        //String convert = String.valueOf(length);
        //Log.d("STATE", convert);

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

        targetArea = this.findViewById(R.id.targetArea);

        targetArea.setOnDragListener(dragListener);

        //redbtn.setOnLongClickListener(longClickListener);
        //bluebtn.setOnLongClickListener(longClickListener);
        //greenbtn.setOnLongClickListener(longClickListener);
        //blackbtn.setOnLongClickListener(longClickListener);

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

        redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            list.add("red");
            if(!btn1Colored){
                bbtn1.setBackgroundColor(Color.RED);
                btn1Colored = true;
                bbtn1.setVisibility(View.VISIBLE);
            } else if (!btn2Colored){
                bbtn2.setBackgroundColor(Color.RED);
                btn2Colored = true;
                bbtn2.setVisibility(View.VISIBLE);
            } else if (!btn3Colored){
                bbtn3.setBackgroundColor(Color.RED);
                btn3Colored = true;
                bbtn3.setVisibility(View.VISIBLE);
            } else if (!btn4Colored){
                bbtn4.setBackgroundColor(Color.RED);
                btn4Colored = true;
                bbtn4.setVisibility(View.VISIBLE);
            }
            }
        });

        bluebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("blue");
                if(!btn1Colored){
                    bbtn1.setBackgroundColor(Color.BLUE);
                    btn1Colored = true;
                    bbtn1.setVisibility(View.VISIBLE);
                } else if (!btn2Colored){
                    bbtn2.setBackgroundColor(Color.BLUE);
                    btn2Colored = true;
                    bbtn2.setVisibility(View.VISIBLE);
                } else if (!btn3Colored){
                    bbtn3.setBackgroundColor(Color.BLUE);
                    btn3Colored = true;
                    bbtn3.setVisibility(View.VISIBLE);
                } else if (!btn4Colored){
                    bbtn4.setBackgroundColor(Color.BLUE);
                    btn4Colored = true;
                    bbtn4.setVisibility(View.VISIBLE);
                }
            }
        });

        greenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("green");
                if(!btn1Colored){
                    bbtn1.setBackgroundColor(Color.GREEN);
                    btn1Colored = true;
                    bbtn1.setVisibility(View.VISIBLE);
                } else if (!btn2Colored){
                    bbtn2.setBackgroundColor(Color.GREEN);
                    btn2Colored = true;
                    bbtn2.setVisibility(View.VISIBLE);
                } else if (!btn3Colored){
                    bbtn3.setBackgroundColor(Color.GREEN);
                    btn3Colored = true;
                    bbtn3.setVisibility(View.VISIBLE);
                } else if (!btn4Colored){
                    bbtn4.setBackgroundColor(Color.GREEN);
                    btn4Colored = true;
                    bbtn4.setVisibility(View.VISIBLE);
                }
            }
        });

        blackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("black");
                if(!btn1Colored){
                    bbtn1.setBackgroundColor(Color.BLACK);
                    btn1Colored = true;
                    bbtn1.setVisibility(View.VISIBLE);
                } else if (!btn2Colored){
                    bbtn2.setBackgroundColor(Color.BLACK);
                    btn2Colored = true;
                    bbtn2.setVisibility(View.VISIBLE);
                } else if (!btn3Colored){
                    bbtn3.setBackgroundColor(Color.BLACK);
                    btn3Colored = true;
                    bbtn3.setVisibility(View.VISIBLE);
                } else if (!btn4Colored){
                    bbtn4.setBackgroundColor(Color.BLACK);
                    btn4Colored = true;
                    bbtn4.setVisibility(View.VISIBLE);
                }
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

       /* redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override

        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);

            return true;
        }

    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override

        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View)event.getLocalState();

            switch (dragEvent) {

                case DragEvent.ACTION_DRAG_ENTERED:


                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                        LinearLayout oldparent = (LinearLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout)v;
                        newParent.addView(view);

                        Toast.makeText(Main3Activity.this, "Dropped", Toast.LENGTH_SHORT).show();

                    break;
            }
            return true;
        }

    };

    private void disconnect () {
        //Log.d("STATE", gameLobby);
        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
        startActivity(intent);
    }

    private void clear () {
        targetArea.removeAllViews();
    }
}
