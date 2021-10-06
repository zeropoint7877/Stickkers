package com.app.Stickkers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.app.Stickkers.Utils.Functions;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stickkers.Interfaces.StickkerCallback;
import com.stickkers.Views.StickkerView;


public class StickerViewActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton uploadStikerBtn;
    SimpleDraweeView draweeView;
    StickkerView stickkerView;
    EditText msgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_view);

        draweeView = findViewById(R.id.gifpreview);
        uploadStikerBtn = findViewById(R.id.upload_stiker_btn);
        msgEdit = findViewById(R.id.msgEdit);

        stickkerView = findViewById(R.id.giphy);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        stickkerView.initialize(StickerViewActivity.this, Constants.SDK_API_KEY);

        stickkerView.setSdkView(stickkerView, uploadStikerBtn, msgEdit);

        stickkerView.setSelectedCallback(new StickkerCallback() {
            @Override
            public void onGifSelected(String uri) {
                Log.d("stickkers_", "image uri at activity: " + uri.toString());
                Functions.showStickerFresco(uri, draweeView);
            }
        });
        findViewById(R.id.backbtn).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (stickkerView.getVisibility() == View.VISIBLE) {
            stickkerView.setCallback();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backbtn:
                onBackPressed();
                break;

            default:
                break;

        }
    }

}