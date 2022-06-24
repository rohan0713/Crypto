package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class addressActivity extends AppCompatActivity {


    public final static int QRCodeWidth = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ImageView imageView = findViewById(R.id.code);
        TextView address = findViewById(R.id.address);
        ImageView copy = findViewById(R.id.copy);
        String ad = String.valueOf(userHelperClass.get(addressActivity.this,
                        "ad", "ad"));
        address.setText(ad);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", ad.toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(addressActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();

            }
        });

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(String.valueOf(userHelperClass.get(addressActivity.this,
                            "ad", "ad")),
                    BarcodeFormat.DATA_MATRIX.QR_CODE, QRCodeWidth, QRCodeWidth, null);
        } catch (IllegalArgumentException | WriterException e) {
            
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offSet = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offSet + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 200, 0, 0, bitMatrixWidth, bitMatrixHeight);
        imageView.setImageBitmap(bitmap);
    }
}