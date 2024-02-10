package com.fjd.firma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signatureView = findViewById(R.id.signatureView);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnClear = findViewById(R.id.btnClear);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSignature();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
            }
        });
    }

    private void saveSignature() {
        Bitmap signatureBitmap = signatureView.getSignatureBitmap();
        if (signatureBitmap != null) {
            String path = SignatureUtil.saveSignatureImage(this, signatureBitmap);
            if (path != null) {
                Toast.makeText(this, "Firma guardada en " + path, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al guardar la firma", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se ha dibujado ninguna firma", Toast.LENGTH_SHORT).show();
        }
    }
}
