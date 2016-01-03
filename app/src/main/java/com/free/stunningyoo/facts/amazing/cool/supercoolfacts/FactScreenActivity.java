package com.free.stunningyoo.facts.amazing.cool.supercoolfacts;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;

public class FactScreenActivity extends AppCompatActivity {
    //AppCompatActivity
    Button button;
    TextView view;
    FactReader factReader;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_screen);
        String fontPath = "Oswald-Light.ttf";

        //add code
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // text view label
        TextView txtGhost = (TextView) findViewById(R.id.textView);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtGhost.setTypeface(tf);

        context=getApplicationContext();
        factReader=new FactReader(context);
        button= (Button) findViewById(R.id.button);
        view= (TextView) findViewById(R.id.textView);
        view.addTextChangedListener(textAutoResizeWatcher(view, 25, 50));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    view.setText(factReader.ReadFacts());
                } catch (IOException e) {
                    System.out.print("SSSSSSsssssssss");
                    e.printStackTrace();
                }

            }
        });

    }
    private TextWatcher textAutoResizeWatcher(final TextView view, final int MIN_SP, final int MAX_SP) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {

                final int widthLimitPixels = view.getWidth() - view.getPaddingRight() - view.getPaddingLeft();
                Paint paint = new Paint();
                float fontSizeSP = pixelsToSp(view.getTextSize());
                paint.setTextSize(spToPixels(fontSizeSP));

                String viewText = view.getText().toString();

                float widthPixels = paint.measureText(viewText);

                // Increase font size if necessary.
                if (widthPixels < widthLimitPixels){
                    while (widthPixels < widthLimitPixels && fontSizeSP <= MAX_SP){
                        ++fontSizeSP;
                        paint.setTextSize(spToPixels(fontSizeSP));
                        widthPixels = paint.measureText(viewText);
                    }
                    --fontSizeSP;
                }
                // Decrease font size if necessary.
                else {
                    while (widthPixels > widthLimitPixels || fontSizeSP > MAX_SP) {
                        if (fontSizeSP < MIN_SP) {
                            fontSizeSP = MIN_SP;
                            break;
                        }
                        --fontSizeSP;
                        paint.setTextSize(spToPixels(fontSizeSP));
                        widthPixels = paint.measureText(viewText);
                    }
                }

                view.setTextSize(fontSizeSP);
            }
        };
    }

    private float pixelsToSp(float px) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }

    private float spToPixels(float sp) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return sp * scaledDensity;
    }







}
