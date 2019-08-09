package com.karimh16.books;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.karimh16.books.databinding.ActivityBookDetailBinding;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Book book = getIntent().getParcelableExtra("Book");
        ActivityBookDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail);
        binding.setBook(book);
    }
}
