package com.example.myapplication;

import android.provider.BaseColumns;

// klasa kontraktu - odwzorowuje schemat bazy danych
public final class DaneOsoboweContract
{
	// konstruktor prywatny uniemo�liwia tworzenie obiekt�w
	private DaneOsoboweContract() {}

	// klasa wewn�trzna definiuje kolumny tabeli o nazwie dane_osobowe
	public static class DaneOsoboweTabela implements BaseColumns
	{
		public static final String NAZWA_TABELI = "dane_osobowe";
		public static final String KOLUMNA_NAZWISKO = "nazwisko";
		public static final String KOLUMNA_IMIE = "imie";
		public static final String KOLUMNA_TELEFON = "telefon";
	}
}
