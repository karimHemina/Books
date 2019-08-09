package com.karimh16.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText etTitle = (EditText) findViewById(R.id.etTitle);
        final EditText etAuthor = (EditText) findViewById(R.id.etAuthor);
        final EditText etPublisher = (EditText) findViewById(R.id.etPublisher);
        final EditText etIsbn = (EditText) findViewById(R.id.etIsbn);
        final Button button = (Button) findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                String publisher = etPublisher.getText().toString().trim();
                String isbn = etIsbn.getText().toString().trim();
                if(title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()){
                    String message = getString(R.string.no_search_data);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }else{
                    URL queryUrl = ApiUtil.buildUrl(title, author, publisher, isbn);
                    //sharedPreferences
                    Context context = getApplicationContext();
                    int position = SpUtil.getPreferencesInt(context, SpUtil.POSITION);
                    if(position == 0 || position == 5){
                        position = 1;
                    }else{
                        position++;
                    }
                    String key = SpUtil.QUERY + String.valueOf(position);
                    String value = title + "," + author + "," + publisher + "," + isbn;
                    SpUtil.setPrefrenceString(context, key, value);
                    SpUtil.setPrefrenceInt(context, SpUtil.POSITION, position);

                    Intent intent = new Intent(getApplicationContext(), BooksListActivity.class);
                    intent.putExtra("Query", queryUrl.toString());
                    startActivity(intent);
                }
            }
        });
    }
}
