package com.example.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class AddPracticeActivity1 extends AppCompatActivity {
    final int NO_INPUT = Integer.MAX_VALUE;

    EditText editText_title;
    ImageView iv_scope;
    Spinner sort_spinner, sensitivity_spinner, gender_spinner;
    Button btn_cancel, btn_record;

    String scope = "PUBLIC", sort = "", gender="";
    int sensitivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpractice1);
        setTitle("새 연습 추가");

        editText_title = (EditText)findViewById(R.id.editText_title);
        iv_scope = (ImageView)findViewById(R.id.iv_scope);
        sort_spinner = findViewById(R.id.sort_spinner);
        sensitivity_spinner = findViewById(R.id.sensitivity_spinner);
        gender_spinner = findViewById(R.id.gender_spinner);

        // Sort Spinner
        String[] itemList1 = {"ONLINE", "OFFLINE"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itemList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sort_spinner.setAdapter(adapter1);

        sort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sort = itemList1[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Sensitivity Spinner
        String[] itemList2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itemList2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sensitivity_spinner.setAdapter(adapter2);

        sensitivity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sensitivity = Integer.parseInt(itemList2[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Gender Spinner
        String[] itemList3 = {"WOMEN", "MEN"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itemList3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(adapter3);

        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = itemList3[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_record = findViewById(R.id.btn_record);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // xml에서 호출
    public void insert(View v) {
        String title = editText_title.getText().toString();
        if (title.contains("'")) {
            title = title.replaceAll("'", "\'\'");
        }
        if (!title.equals("")) {
            //String sql = "INSERT INTO practiceTable (practice_id,file_url) VALUES ('" + NO_INPUT + "', " + curYear + ", " + curMonth +", " + curDate + ", " + curHour + ", " + curMinute + ", '" + AmPm + "', " + isStarFilled +", " + 0 + ", '', 0);";
            //db.execSQL(sql);

            //finish();

            Intent intent = new Intent(AddPracticeActivity1.this, RecordActivity1.class);
            intent.putExtra("title", title);
            intent.putExtra("scope", scope);
            intent.putExtra("sort", sort);
            intent.putExtra("sensitivity", sensitivity);
            intent.putExtra("gender", gender);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "내용을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // xml에서 호출
    public void setScopeImg(View view) {
        if (scope.equals("PUBLIC")) {
            iv_scope.setImageResource(R.drawable.ic_star_filled);
            scope = "PRIVATE";
        } else if (scope.equals("PRIVATE")) {
            iv_scope.setImageResource(R.drawable.ic_star_empty);
            scope = "PUBLIC";
        }
    }
}
