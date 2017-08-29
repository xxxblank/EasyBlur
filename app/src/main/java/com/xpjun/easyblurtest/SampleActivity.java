package com.xpjun.easyblurtest;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpjun.library.BlurDialogBuilder;
import com.xpjun.library.BlurPolice;
import com.xpjun.library.EasyBlur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleActivity extends Activity {

    private Button dialog;
    private ImageView img;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_EXTERNAL_CREATE = 2;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static String[] PERMISSION_CREATE = {
            "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"
    };

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
            int createPermission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.MOUNT_UNMOUNT_FILESYSTEMS");
            if (createPermission!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity,PERMISSION_CREATE,REQUEST_EXTERNAL_CREATE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);

        dialog =  findViewById(R.id.dialog);
        img =  findViewById(R.id.img);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EasyBlur.BlurDialog dialog =
                        new BlurDialogBuilder()
                                .bind(SampleActivity.this)
                                .setPolice(BlurPolice.rsBlur)
                                .setTitle("test")
                                .setMessage("this is a dialog with blur background")
                                .setPositiveBtText("yes")
                                .setNegativeBtText("no")
                                .setRadius(3)
                                .setMultiReduce(3)
                                .setDimming(true)
                                .build();
                dialog.show();
            }
        });

        findViewById(R.id.file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //savePic("");
                String pathResult = getExternalFilesDir(null)+"/testImg/"+"test2.jpg";
                File file = new File(pathResult);
                if (file.exists()){
                    Log.e("Sample","file  exists");
                    EasyBlur.getInstance().blur(file).police(BlurPolice.rsBlur).into(img);
                }

            }
        });

        findViewById(R.id.bitmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.head2);
                EasyBlur.getInstance().blur(bitmap).police(BlurPolice.rsBlur).into(img);
            }
        });

        findViewById(R.id.path).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyBlur.getInstance().blur(getExternalFilesDir(null)+"/testImg/"+"test2.jpg")
                        .police(BlurPolice.rsBlur).into(img);
            }
        });

        findViewById(R.id.res).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyBlur.getInstance().blur(R.drawable.head2).police(BlurPolice.rsBlur).into(img);
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.head2);
            }
        });
    }

    private void savePic(String path) {
        File file = new File(getExternalFilesDir(null)+"/testImg/");
        if (!file.exists()){
            file.mkdirs();
        }
        File file1 = new File(file,"test2.jpg");
        Log.e("Sample",file.getAbsolutePath());
        try {
            FileOutputStream out = new FileOutputStream(file1);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.head2);
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
