package au.edu.jcu.cp3406.reflexchecker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    double elapsed_time;
    public TextView time_used;
    public Button start_btn;
    public String restart = "RESTART";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_used = findViewById(R.id.time_used);
        start_btn = findViewById(R.id.start_btn);

    }

    public void startTest(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivityForResult(intent, GameActivity.GAME_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GameActivity.GAME_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data!=null) {
                    elapsed_time = data.getDoubleExtra("elapsed_time", 0);
                }
            }
        }
        String time_format = String.format(Locale.US,"Time used: %,.2f seconds",elapsed_time);
        time_used.setText(time_format);
        start_btn.setText(restart);
    }
}