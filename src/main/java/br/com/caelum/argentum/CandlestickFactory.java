package br.com.caelum.argentum;

import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data,
			List<Negocio> negocios) {

		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(
				negocios.size() - 1).getPreco();

		double maximo = negocios.isEmpty() ? 0 : Double.MIN_VALUE;
		double minimo = negocios.isEmpty() ? 0 : Double.MAX_VALUE;
		double volume = 0;

		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();
			if (negocio.getPreco() > maximo) {
				maximo = negocio.getPreco();
			}
			if (negocio.getPreco() < minimo) {
				minimo = negocio.getPreco();
			}
		}

		return new Candlestick(abertura, fechamento, minimo, maximo, volume,
				data);

	}
}
