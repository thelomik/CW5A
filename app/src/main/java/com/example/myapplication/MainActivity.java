package com.example.myapplication;



import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.EditText;

import com.example.myapplication.ZawartoscBazyActivity;



public class MainActivity extends Activity implements OnClickListener

{

	private DaneOsoboweDbHelper dbHelper;

	private EditText et1, et2, et3,et4;



	@Override

	protected void onCreate(Bundle savedInstanceState)

	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		Button btn1 = (Button) findViewById(R.id.button1);

		Button btn2 = (Button) findViewById(R.id.button2);

		Button btn3 = (Button) findViewById(R.id.button3);

		Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);
		btn1.setOnClickListener(this);

		btn2.setOnClickListener(this);

		btn3.setOnClickListener(this);

		btn4.setOnClickListener(this);

		btn5.setOnClickListener(this);

		et1 = (EditText) this.findViewById(R.id.editText1);

		et2 = (EditText) this.findViewById(R.id.editText2);

		et3 = (EditText) this.findViewById(R.id.editText3);
		et4 = (EditText) this.findViewById(R.id.editText4);

		dbHelper = new DaneOsoboweDbHelper(this);

		dbHelper.dodajWiersz("Kowalski", "Jan", "600-100-100"); // pocz�tkowe wpisy do bazy

		dbHelper.dodajWiersz("Nowak", "Jerzy", "600-200-200");

		dbHelper.dodajWiersz("Malinowski", "Marek", "600-300-300");

	}



	@Override

	protected void onDestroy()

	{

		dbHelper.close();

		super.onDestroy();
	}



	public void onClick(View v)

	{

		switch (v.getId())

		{

			case R.id.button1: // dodawanie danych do bazy

				String s1 = et1.getText().toString(); // pobranie danych z GUI

				String s2 = et2.getText().toString();

				String s3 = et3.getText().toString();

				dbHelper.dodajWiersz(s1, s2, s3); // zapis wiersza do bazy

				et1.setText(""); et2.setText(""); et3.setText("");

				break;
			case R.id.button2: // uruchomienie aktywno�ci do wy�wietlania zawarto�ci bazy
				Intent intencja = new Intent(this, ZawartoscBazyActivity.class);

				startActivity(intencja);

				break;

			case R.id.button3: // usuwanie danych z bazy

				dbHelper.usunWszystko();

				break;

			case R.id.button4: // zako�czenie aplikacji

				this.finish();

				break;

			case R.id.button5:
				dbHelper.usunWiersz(Long.parseLong(String.valueOf(et4.getText())));

				break;
		}

	}

}    
