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

    long finish_time;
    long start_time;
    long elapsed_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTest(View view) {
        start_time = System.nanoTime();
        Intent intent = new Intent(this, GameActivity.class);
        startActivityForResult(intent, GameActivity.GAME_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GameActivity.GAME_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data!=null) {
                    finish_time = data.getLongExtra("finish_time", 0);
                }
            }
        }
        elapsed_time = finish_time - start_time;
        double elapsed_time_second = elapsed_time / 1_000_000_000.0;
        TextView time_used = findViewById(R.id.time_used);
        String time_format = String.format(Locale.US,"Time used: %,.2f seconds",elapsed_time_second);
        time_used.setText(time_format);
        Button start = findViewById(R.id.start_btn);
        String restart = "RESTART";
        start.setText(restart);
    }
}