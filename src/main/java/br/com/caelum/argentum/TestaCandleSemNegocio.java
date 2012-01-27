package br.com.caelum.argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandleSemNegocio {
	public static void main(String... args) {
		Calendar hoje = Calendar.getInstance();

		List<Negocio> negocios = Arrays.asList();

		CandlestickFactory fabrica = new CandlestickFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negocios);

		System.out.println(candle);
	}
}
