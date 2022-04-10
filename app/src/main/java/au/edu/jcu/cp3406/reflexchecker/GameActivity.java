package au.edu.jcu.cp3406.reflexchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static int GAME_REQUEST = 12345;
    private final Random random = new Random();
    private static final int[] drawables = {
            R.drawable.drink_icon,
            R.drawable.food_icon,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupDescription(R.id.task1, R.array.task1_descriptions);
        setupDescription(R.id.task2, R.array.task2_descriptions);

        int[] checkbox_array = {R.array.drinks, R.array.foods};

        for (int i = 0; i < 5; i++) {
            addImage();
            int index = random.nextInt(checkbox_array.length);
            int arrayID = checkbox_array[index];
            addCheckboxes(arrayID);
        }
    }

    private void setupDescription(int taskID, int arrayID) {
        TextView task = findViewById(taskID);
        String[] descriptions = getResources().getStringArray(arrayID);

        int i = random.nextInt(descriptions.length);
        task.setText(descriptions[i]);
    }

    private void addImage() {
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.image, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount()-1);
        ImageView image = lastChild.findViewById(R.id.image);

        int index = random.nextInt(drawables.length);
        image.setImageDrawable(getResources().getDrawableForDensity(drawables[index], 0));
    }

    private void addCheckboxes(int arrayID){
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.checkboxes, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount()-1);
        TableRow checkboxes = lastChild.findViewById(R.id.checkboxes);

        int checkbox_num = checkboxes.getChildCount();
        String[] items = getResources().getStringArray(arrayID);

        for (int i = 0; i < checkbox_num; i++) {
            CheckBox checkBox = (CheckBox) checkboxes.getChildAt(i);
            int index = random.nextInt(items.length);
            checkBox.setText(items[index]);
            int random_num = random.nextInt(100);
            if (random_num %2 == 1){
                checkBox.setChecked(true);
            }
        }
    }

    public void doneClicked(){
        Intent data = new Intent();
        data.putExtra("finish_time",System.nanoTime());
        setResult(RESULT_OK, data);
        finish();
    }
}