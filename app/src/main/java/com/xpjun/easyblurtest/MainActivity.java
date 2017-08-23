package com.xpjun.easyblurtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpjun.library.BlurDialogBuilder;
import com.xpjun.library.EasyBlur;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.img);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyBlur.BlurDialog dialog =
                        new BlurDialogBuilder()
                                .setTitle("test")
                                .setMessage("hhhahdafs")
                                .build()
                                .bind(MainActivity.this);
                dialog.show();
            }
        });
    }
}
