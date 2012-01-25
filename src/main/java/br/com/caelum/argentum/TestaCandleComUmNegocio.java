package br.com.caelum.argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandleComUmNegocio {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		System.out.println(candle);

		System.out.println("Alterando data do candle: ");
		hoje.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(candle);
		Calendar dataTemp = n1.getData();
		dataTemp.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(candle);
		System.out.println("Candle é imutável!!!");
	}
}
