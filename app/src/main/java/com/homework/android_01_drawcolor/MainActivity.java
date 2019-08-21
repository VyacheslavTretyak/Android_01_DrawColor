package com.homework.android_01_drawcolor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout cl;
    TextView tv;
    int r = 0;
    int g = 0;
    int b = 0;
    int offset = 30;
    float x = 255;
    float y = 255;
    float sx;
    float sy;

    int nd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cl = (ConstraintLayout)this.findViewById(R.id.cl);

        tv = (TextView) this.findViewById(R.id.tv);
        tv.setBackgroundColor(Color.rgb(127, 127, 127));

        cl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_MOVE:
                        x = Math.abs(motionEvent.getX() - sx);
                        y = Math.abs(motionEvent.getY() - sy);
//                        x = Math.abs(x / cl.getMeasuredWidth() * 256);
//                        y = Math.abs( y/cl.getMeasuredHeight() * 256);
                        if(x>20 && y<offset){
                            r = (int)Math.abs(x / cl.getMeasuredWidth() * 256);
                        }else if(x<offset && y>offset){
                            b = (int)Math.abs(y / cl.getMeasuredHeight() * 256);
                        }else if(x>offset && y>offset){
                            int mx = cl.getMeasuredWidth();
                            int my = cl.getMeasuredHeight();
                            int md =  (int)Math.sqrt(mx*mx + my*my);
                            float l = (float) Math.sqrt(x*x + y*y);
                            g = (int)Math.abs(l / md * 256);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        sx = motionEvent.getX();
                        sy = motionEvent.getY();
                        break;
                }
                tv.setText("R:"+r+" G:"+g+" B:"+b);
                cl.setBackgroundColor(Color.rgb(r, g, b));
                return true;
            }
        });
    }
}
