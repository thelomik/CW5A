package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ZawartoscBazyActivity extends Activity
{
	private TextView tv;
	private DaneOsoboweDbHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zawartosc);
		tv = (TextView) findViewById(R.id.textView4);
        	dbHelper = new DaneOsoboweDbHelper(this);
        	wyswietlBaze();
	}
	
    	@Override
    	protected void onDestroy()
    	{
    		dbHelper.close();
    		super.onDestroy();
    	}
	
	private void wyswietlBaze()
	{
    		tv.setText("");
    		Cursor kursor = dbHelper.odczytajWszystko();
    		if (kursor.getCount() == 0)
    			tv.setText("Baza jest pusta");
    		while (kursor.moveToNext())
    		{
    			long nr = kursor.getLong(0);      // kolumna 0 - klucz podstawowy
    			String naz = kursor.getString(1); // kolumna 1 - nazwisko
    			String im = kursor.getString(2);  // kolumna 2 - imie
    			String tel = kursor.getString(3); // kolumna 3 - telefon    		
    			String s = tv.getText().toString();
    			tv.setText(s + "\n" + nr + " " + naz + " " + im + " " + tel);
    		}
    		kursor.close();
	}
}
