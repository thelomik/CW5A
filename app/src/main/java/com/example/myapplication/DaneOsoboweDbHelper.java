package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.myapplication.DaneOsoboweContract.DaneOsoboweTabela;

public class DaneOsoboweDbHelper extends SQLiteOpenHelper
{
	private static final String SQL_CREATE_ENTRIES =
		"CREATE TABLE " + DaneOsoboweTabela.NAZWA_TABELI + " (" +
		DaneOsoboweTabela._ID + " INTEGER PRIMARY KEY," +
		DaneOsoboweTabela.KOLUMNA_NAZWISKO + " TEXT," +
		DaneOsoboweTabela.KOLUMNA_IMIE + " TEXT," +
		DaneOsoboweTabela.KOLUMNA_TELEFON + " TEXT)";

	private static final String SQL_DELETE_ENTRIES =
		"DROP TABLE IF EXISTS " + DaneOsoboweTabela.NAZWA_TABELI;
	
	public static final int DATABASE_VERSION = 1; // wersja bazy (zwi�kszana przy zmianie schematu tabel)
	public static final String DATABASE_NAME = "DaneOsobowe.db"; // nazwa pliku bazy danych

	public DaneOsoboweDbHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(SQL_CREATE_ENTRIES);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// w przyk�adzie baza to cache dla danych online, dlatego aktualizacja to:
		db.execSQL(SQL_DELETE_ENTRIES); // usuni�cie danych
		onCreate(db); // utworzenie bazy od nowa
	}
		
	public void dodajWiersz(String s1, String s2, String s3)
	{
	    	SQLiteDatabase db = getWritableDatabase(); // dost�p w trybie do zapisu
    		ContentValues values = new ContentValues(); // mapa warto�ci
    		values.put(DaneOsoboweTabela.KOLUMNA_NAZWISKO, s1); // nazwy kolumn to klucze
    		values.put(DaneOsoboweTabela.KOLUMNA_IMIE, s2);
    		values.put(DaneOsoboweTabela.KOLUMNA_TELEFON, s3);
    		db.insert(DaneOsoboweTabela.NAZWA_TABELI, null, values); // wstawienie wiersza (rekordu)
	}
	
	public Cursor odczytajWszystko()
	{
    		SQLiteDatabase db = getReadableDatabase(); // dost�p w trybie do odczytu
    		String[] kolumny = { BaseColumns._ID, DaneOsoboweTabela.KOLUMNA_NAZWISKO,
    			             DaneOsoboweTabela.KOLUMNA_IMIE, DaneOsoboweTabela.KOLUMNA_TELEFON };
    		Cursor cursor = db.query(DaneOsoboweTabela.NAZWA_TABELI, kolumny, null, null, null, null, null);
    	                         // tabela do przeszukania, tablica nazw kolumn do zwr�cenia (null to wszystkie)
    		return cursor;
	}
	
	public void usunWszystko()
	{
    		SQLiteDatabase db = getWritableDatabase(); // dost�p w trybie do zapisu
    		db.delete(DaneOsoboweTabela.NAZWA_TABELI, null, null); // usuni�cie wszystkich wierszy		
	}

	public void usunWiersz(Long id){

		SQLiteDatabase db = this.getWritableDatabase();
		try
		{
			 db.delete(DaneOsoboweTabela.NAZWA_TABELI, "_id = ?", new String[]{Long.toString(id)});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.close();
		}
	}
	}

