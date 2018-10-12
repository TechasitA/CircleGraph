package com.example.porbook.circlegraph;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ShowGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_graph);

        // get intent and get values from previous activity
        Intent intent = getIntent();
        String graphName = intent.getStringExtra("graphName");
        int partAmt = Integer.parseInt(intent.getStringExtra("partAmt"));

        // set graph name
        TextView graphNameTw = findViewById(R.id.graphNameTw);
        graphNameTw.setText(graphName);

        // draw circle graph
        drawGraph(partAmt);
    }

    public void goBack(View view) {
        finish();
    }

    private void drawGraph(int n) {
        ImageView graphIv = findViewById(R.id.graphIv);

        Log.i("MyLog", "test");

        // parameter 200 is now fix because can't solve get width problem yet.
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getHeight()/2, paint);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float startAngle = 0;
        float sweepAngle = 0;
        Random random = new Random();
        int red, green, blue, alpha;
        for (int i=1; i<=n; i++) {
            // calculate angle and random color for make graph each part
            startAngle += sweepAngle;
            sweepAngle = (float)((Math.pow(2, n-i)/(Math.pow(2, n)-1))*360);
            alpha = 255-random.nextInt(100);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            Log.i("MyLog", "start: "+startAngle+", sweep: "+sweepAngle);

            // draw part of graph
                // fill part
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(alpha, red, green, blue));
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
                // draw line
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
        }

        graphIv.setImageBitmap(bitmap);
    }
}
