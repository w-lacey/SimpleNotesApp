package com.example.onethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class EnterNote extends AppCompatActivity {

  EditText noteTitle;
  EditText noteContent;
  Button saveNote;
  String title;
  String content;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_enter_note);
    noteTitle = findViewById(R.id.noteTitleInput);
    noteContent = findViewById(R.id.noteContentInput);
    saveNote = findViewById(R.id.saveNoteButton);
    String extraNoteContent = getIntent().getStringExtra("noteContent");
    String extraNoteTitle = getIntent().getStringExtra("noteTitle");
    // Accessing the data using key and value
    if (extraNoteTitle != null && extraNoteContent != null) {
      noteTitle.setText(extraNoteTitle);
      noteContent.setText(extraNoteContent);
    }

    saveNote.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            title = noteTitle.getText().toString();
            content = noteContent.getText().toString();
            try {
              Note newNote = new Note(title, content);
              JsonHandler jh = new JsonHandler(newNote, getApplicationContext());
              Intent intent = new Intent(EnterNote.this, NoteList.class);
              startActivity(intent);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
  }
}
