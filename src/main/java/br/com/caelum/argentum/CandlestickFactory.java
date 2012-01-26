package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

	public boolean isMesmoDia(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2
				.get(Calendar.DAY_OF_MONTH)
				&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
				&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candlestick> constroiCandles(List<Negocio> negocios) {
		Collections.sort(negocios);

		List<Candlestick> candles = new ArrayList<Candlestick>();
		List<Negocio> negociosDoMesmoDia = new ArrayList<Negocio>();
		Calendar dataTemp = negocios.get(0).getData();

		for (Negocio negocio : negocios) {
			if (!isMesmoDia(dataTemp, negocio.getData())) {
				candles.add(constroiCandleParaData(dataTemp, negociosDoMesmoDia));
				negociosDoMesmoDia.clear();
				dataTemp = negocio.getData();
			}
			negociosDoMesmoDia.add(negocio);
		}

		candles.add(constroiCandleParaData(dataTemp, negociosDoMesmoDia));

		return candles;
	}
}
