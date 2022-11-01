package com.example.onethingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class NoteList extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note_list);
    ListView noteListView = findViewById(R.id.noteListView);
    Button newNoteButton = findViewById(R.id.newNoteButton);
    JsonHandler jh = new JsonHandler();
    try {
      ArrayList<Note> notes = jh.deserialize(getApplicationContext());
      ArrayAdapter<Note> arrayAdapter =
          new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
      noteListView.setAdapter(arrayAdapter);

      noteListView.setOnItemLongClickListener(
          new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(
                AdapterView<?> parent, View view, int position, long id) {
              AlertDialog.Builder showPlace = new AlertDialog.Builder(NoteList.this);
              showPlace.setMessage("Remove from list?");

              new AlertDialog.Builder(NoteList.this)
                  .setIcon(android.R.drawable.ic_dialog_alert)
                  .setMessage("Are You Sure You Want to Delete This Note?!")
                  .setTitle("Attempt to Delete A Note")
                  .setPositiveButton(
                      "YES",
                      new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          try {
                            if (notes != null) {
                              notes.remove(position);
                              arrayAdapter.notifyDataSetChanged();
                              JsonHandler updateJson =
                                  new JsonHandler(getApplicationContext(), notes);
                            }
                          } catch (Exception e) {
                            e.printStackTrace();
                          }
                        }
                      })
                  .setNegativeButton(
                      "NO",
                      new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          Toast.makeText(NoteList.this, "Good Choice", Toast.LENGTH_SHORT).show();
                        }
                      })
                  .show();
              return true;
            }

            public void onItemClick(
                AdapterView<?> adapterView, View listView, int position, long l) {
              AlertDialog.Builder showPlace = new AlertDialog.Builder(NoteList.this);
              showPlace.setMessage("Remove from list?");

              new AlertDialog.Builder(NoteList.this)
                  .setIcon(android.R.drawable.ic_dialog_alert)
                  .setMessage("Are You Sure You Want to Delete This Note?!")
                  .setTitle("Attempt to Delete A Note")
                  .setPositiveButton(
                      "YES",
                      new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          try {
                            if (notes != null) {
                              notes.remove(position);
                              arrayAdapter.notifyDataSetChanged();
                              JsonHandler updateJson =
                                  new JsonHandler(getApplicationContext(), notes);
                            }
                          } catch (Exception e) {
                            e.printStackTrace();
                          }
                        }
                      })
                  .setNegativeButton(
                      "NO",
                      new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          Toast.makeText(NoteList.this, "Good Choice", Toast.LENGTH_SHORT).show();
                        }
                      })
                  .show();
            }
          });
      noteListView.setOnItemClickListener(
          new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent =
                  new Intent(NoteList.this, EnterNote.class)
                      .putExtra("noteContent", notes.get(position).getContent().toString())
                      .putExtra("noteTitle", notes.get(position).getTitle().toString());
              notes.remove(position);
              arrayAdapter.notifyDataSetChanged();
              try {
                JsonHandler updateJson = new JsonHandler(getApplicationContext(), notes);
              } catch (IOException e) {
                e.printStackTrace();
              }
              startActivity(intent);
            }
          });
      newNoteButton.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(NoteList.this, EnterNote.class);
              startActivity(intent);
            }
          });

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
