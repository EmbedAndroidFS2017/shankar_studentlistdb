package ch.ffhs.fs17.esa.studentlistedb;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowList extends ListActivity implements android.view.View.OnClickListener {
    Button btnAdd, btnGetAll;
    TextView studentId;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)){
            Intent intent = new Intent(this, StudentDetail.class);
            intent.putExtra("studentId",0);
            startActivity(intent);
        }
        else {
            StudentRepo repo = new StudentRepo(this);

            ArrayList<HashMap<String, String>> studentList =  repo.getStudentList();
            if(studentList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        studentId = (TextView) view.findViewById(R.id.STUDENT_ID);
                        String studentId = ShowList.this.studentId.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), StudentDetail.class);
                        objIndent.putExtra("studentId", Integer.parseInt( studentId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( ShowList.this, studentList, R.layout.student_entry, new String[] { "id","name"}, new int[] {R.id.STUDENT_ID, R.id.student_name});
                setListAdapter(adapter);
            }
            else {
                Toast.makeText(this,"Kein Student vorhanden!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
    }
}
