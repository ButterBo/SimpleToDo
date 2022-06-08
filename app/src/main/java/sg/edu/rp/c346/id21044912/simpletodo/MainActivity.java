package sg.edu.rp.c346.id21044912.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddDel;
    EditText etElement;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView lvToDo;
    ArrayList<String> arrToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddDel = findViewById(R.id.spinner);
        etElement = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDel);
        btnClear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.listViewDO);
        arrToDo = new ArrayList<String>();
        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrToDo);
        lvToDo.setAdapter(aaToDo);

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spnAddDel.getSelectedItemPosition()){
                    case 0:
                        etElement.setHint("Type in a new task here");
                        etElement.setText("");
                        etElement.setInputType(InputType.TYPE_CLASS_TEXT);
                        btnDel.setEnabled(false);
                        btnDel.setTextColor(Color.parseColor("#A9A9A9"));
                        btnAdd.setEnabled(true);
                        btnAdd.setTextColor(Color.parseColor("#FF000000"));
                        break;
                    case 1:
                        etElement.setHint("Type in the index of the task to be removed");
                        etElement.setText("");
                        etElement.setInputType(InputType.TYPE_CLASS_NUMBER);
                        btnDel.setEnabled(true);
                        btnDel.setTextColor(Color.parseColor("#FF000000"));
                        btnAdd.setEnabled(false);
                        btnAdd.setTextColor(Color.parseColor("#A9A9A9"));
                        if (arrToDo.size()==0){
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etElement.getText().toString())){
                    arrToDo.add(etElement.getText().toString());
                    aaToDo.notifyDataSetChanged();
                    etElement.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Enter a task before press the 'Add' button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etElement.getText().toString())){
                    int index = Integer.parseInt(etElement.getText().toString());
                    if (index<arrToDo.size()){
                        arrToDo.remove(index);
                        aaToDo.notifyDataSetChanged();
                        etElement.setText("");
                    } else {
                        Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter a index before pressing the 'Delete' button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrToDo.size()!=0){
                    arrToDo.removeAll(arrToDo);
                    aaToDo.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any task to clear", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
