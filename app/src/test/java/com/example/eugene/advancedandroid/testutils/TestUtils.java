package com.example.eugene.advancedandroid.testutils;

import com.example.eugene.advancedandroid.model.AdapterFactory;
import com.example.eugene.advancedandroid.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class TestUtils {

    private static TestUtils INSTANCE = new TestUtils();

    private static final Moshi TEST_MOSHI = initializeMoshi();

    private TestUtils(){

    }

    public static <T> T loadJson(String path, Type type){
        try{
            String json = getFileString(path);
            return (T) TEST_MOSHI.adapter(type).fromJson(json);
        }catch (IOException e){
            throw new IllegalArgumentException("Could not deserialize: " + path + " into type: " + type);
        }
    }

    private static String getFileString(String path) {
        try{
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        }catch (IOException e){
            throw new IllegalArgumentException("Could not read from resourse at: " + path);
        }
    }

    private static Moshi initializeMoshi(){
        Moshi.Builder builder = new Moshi.Builder();
        builder.add(AdapterFactory.create());
        builder.add(new ZonedDateTimeAdapter());
        return builder.build();
    }
}
