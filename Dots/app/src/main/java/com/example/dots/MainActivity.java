package com.example.dots;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dots.model.Dot;
import com.example.dots.model.Dots;
import com.example.dots.view.DotView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static int DOT_DIAMETER = 20;
    private final Random rand = new Random();
    final Dots dotModel = new Dots();
    DotView dotView;

    private static final class TrackingTouchListener implements View.OnTouchListener {
        private final Dots dots;
        TrackingTouchListener(Dots dots) {this.dots = dots; }
        public boolean onTouch(View view, MotionEvent evt) {
            switch (evt.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    for(int i = 0, n = evt.getHistorySize(); i < n; i++) {
                        addDot(
                                dots,
                                evt.getHistoricalX(i),
                                evt.getHistoricalY(i),
                                evt.getHistoricalPressure(i),
                                evt.getHistoricalSize(i)
                        );
                    }
                    break;
                default:
                    return false;
            }
            addDot(dots, evt.getX(), evt.getY(), evt.getPressure(), evt.getSize());
            return true;
        }
        private void addDot(Dots dots, float x, float y, float p, float s) { dots.addDot(x, y, Color.CYAN, (int) ((p * s * DOT_DIAMETER) + 25)); }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dotView = new DotView(this, (AttributeSet) dotModel);
        dotView.setOnTouchListener(new TrackingTouchListener(dotModel));
        LinearLayout.LayoutParams widgetParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0F);
        dotView.setLayoutParams(widgetParams);
        setContentView(R.layout.activity_main);
        ((LinearLayout) findViewById(R.id.root)).addView(dotView, 0);
        final EditText tb1 = findViewById(R.id.text1);
        final EditText tb2 = findViewById(R.id.text2);
        ((Button) findViewById(R.id.button1)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg) { makeDot(dotModel, dotView, Color.GREEN); }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg) { makeDot(dotModel, dotView, Color.RED); }
        });

        dotModel.setDotsChangeListener(new Dots.DotsChangeListener() {
            @Override
            public void onDotsChange(Dots dots) {
                Dot d = dots.getLastDot();
                tb1.setText((null == d) ? "" : String.valueOf(d.getX()));
                tb2.setText((null == d) ? "" : String.valueOf(d.getY()));
                dotView.invalidate(); }
        });
    }
    void makeDot(Dots dots, DotView view, int color) {
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(
                DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
                DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
                color,
                DOT_DIAMETER);
    }
}