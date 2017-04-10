package ch.ffhs.fs17.esa.studentlistedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cheey on 04.04.2017.
 */

public class StudentDetail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave, btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    private int studentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        studentId =0;
        Intent intent = getIntent();
        studentId =intent.getIntExtra("studentId", 0);
        StudentRepo repo = new StudentRepo(this);
        Student student = new Student();
        student = repo.getStudentById(studentId);

        editTextAge.setText(String.valueOf(student.age));
        editTextName.setText(student.name);
        editTextEmail.setText(student.email);
    }

    public void onClick(View view) {
        StudentRepo repo = new StudentRepo(this);
        if (view == findViewById(R.id.btnSave)){
            Student student = new Student();
            student.age= Integer.parseInt(editTextAge.getText().toString());
            student.email = editTextEmail.getText().toString();
            student.name = editTextName.getText().toString();
            student.studentId = studentId;

            if (studentId ==0){
                studentId = repo.insert(student);
                Toast.makeText(this, "Neue StudentIn hinzugefügt", Toast.LENGTH_SHORT).show();
            }else{
                repo.update(student);
                Toast.makeText(this, "Studentenliste aktualisiert", Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            repo.delete(studentId);
            Toast.makeText(this, "Student gelöscht", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            repo.getStudentList();
            finish();
        }
    }
}

