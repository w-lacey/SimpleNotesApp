package com.example.onethingapp;

import android.content.Context;
import android.os.Build;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;


import java.io.File;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class JsonHandler {

    final Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
    JsonParser jp;

    String jsonNote;
    FileWriter fw;

    Note note;

    JsonHandler(){

    }

    JsonHandler(Context context, ArrayList<Note> noteList) throws IOException {
        File file = new File(context.getFilesDir(), "notes.json");

        FileWriter fw =  new FileWriter(file);
        fw.write(gson.toJson(noteList ));
        fw.close();
        fw.flush();
    }

    JsonHandler(Note note, Context context) throws IOException {

        File file = new File(context.getFilesDir(), "notes.json");

        this.note = note;
        if(!file.exists()){
            fw = new FileWriter(file);
            fw.write("[]");
            fw.close();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        FileReader fr = new FileReader(file);
        final String currentJsonArrayAsString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try (FileWriter fileWriter = new FileWriter(file, false)) {

                JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(note));
                JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
                jsonArray.put(jsonObject);

                fileWriter.write(jsonArray.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Note> deserialize(Context context) throws IOException {
        TypeToken<List<Note>> token = new TypeToken<List<Note>>(){};
        File file = new File(context.getFilesDir(), "notes.json");
        FileReader fw = new FileReader(file);
        List<Note> retrievedNoteList = gson.fromJson(fw, token.getType());
        ArrayList<Note> notes = new ArrayList<>();
        notes.addAll(retrievedNoteList);
        return notes;
    }
}

