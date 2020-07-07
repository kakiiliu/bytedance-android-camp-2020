package com.example.myapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {
    private Paint paint;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if(drawable!=null){
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            Bitmap b = getCilrcleBitmap(bitmap);
            Rect rect = new Rect(0,0,b.getWidth(),b.getHeight());
            Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            canvas.drawBitmap(b,rect,rectDest,paint);

        }else{
            super.onDraw(canvas);
        }
    }

    private Bitmap getCilrcleBitmap(Bitmap bitmap){
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,bitmap.getWidth()/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());

        canvas.drawBitmap(bitmap,rect,rect,paint);
        paint.reset();
        return output;
    }
}
