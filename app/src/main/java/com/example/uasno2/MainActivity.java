package com.example.uasno2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    ObjectAnimator rotateY;
    ObjectAnimator fadeIn;
    ObjectAnimator fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        fadeIn = ObjectAnimator.ofFloat(mImgView, "alpha", 0, 1f);
        fadeOut = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0);
        rotateY = ObjectAnimator.ofFloat(
                mImgView, "rotationY", 180);

//        mImgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                animate();
//            }
//        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();
        int halfWidth = mImgView.getWidth()/2;
        int halfHeight = mImgView.getHeight()/2;

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead(halfWidth, halfHeight);
        drawRightEye(halfWidth, halfHeight);
        drawLeftEye(halfWidth, halfHeight);
        drawEyeConnector(halfWidth, halfHeight);

        animate();
    }

    void animate(){
        fadeIn.setDuration(1000);
        fadeIn.start();

        rotateY.setStartDelay(1000);
        rotateY.getStartDelay();
        rotateY.setDuration(3000);
        rotateY.start();

        fadeOut.setStartDelay(5000);
        fadeOut.getStartDelay();
        fadeOut.setDuration(1000);
        fadeOut.start();
    }

    void drawHead(int halfWidth, int halfHeight){
        RectF rect2 = new RectF(halfWidth-300, halfHeight-200, halfWidth+300, halfHeight+200);
        mCanvas.drawOval(rect2, mHeadPaint);
    }

    void drawRightEye(int halfWidth, int halfHeight){
        mCanvas.drawCircle(halfWidth-120, halfHeight-20, halfHeight/19, mCirclePaint);
    }

    void drawLeftEye(int halfWidth, int halfHeight){
        mCanvas.drawCircle(halfWidth+120, halfHeight-20, halfHeight/19, mCirclePaint);
    }

    void drawEyeConnector(int halfWidth, int halfHeight){
        Rect rect3 = new Rect(halfWidth-120, halfHeight-30, halfWidth+120, halfHeight-10);
        mCanvas.drawRect(rect3, mCirclePaint);
    }
}