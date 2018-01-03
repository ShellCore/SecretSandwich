package com.shellcore.android.secretsandwich;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String fileName = "myFile";

    @BindView(R.id.edtText)
    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save)
    public void saveData() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE));
            writer.write(edtText.getText().toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_charge)
    public void loadData() {
        try {
            InputStream stream = openFileInput(fileName);
            if (stream != null) {
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line)
                            .append("\n");
                }

                stream.close();
                edtText.setText(builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
