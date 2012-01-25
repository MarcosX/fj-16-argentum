package br.com.caelum.argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandlestickFactory {

	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);
		Negocio n2 = new Negocio(45.0, 100, hoje);
		Negocio n3 = new Negocio(39.8, 100, hoje);
		Negocio n4 = new Negocio(42.3, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		System.out.println("Lista de negócios:");
		for (Negocio negocio : negocios) {
			System.out.println(negocio);
		}

		System.out.println("Candle gerado a partir da lista:");
		System.out.println(candle);

		System.out.println("Gerando candle atraves de builder:");
		Candlestick candleBuildado = new CandleBuilder().abertura(10)
				.fechamento(100).minimo(1).maximo(30).volume(1234)
				.data(Calendar.getInstance()).gerarCandle();
		System.out.println(candleBuildado);
	}

}
