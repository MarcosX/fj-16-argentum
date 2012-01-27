package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CandlestickFactory {
	public Candle constroiCandleParaData(Calendar data, List<Negocio> negocios) {

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

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);

	}

	public boolean isMesmoDia(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2
				.get(Calendar.DAY_OF_MONTH)
				&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
				&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candle> constroiCandles(List<Negocio> negocios) {
		Collections.sort(negocios);

		List<Candle> candles = new ArrayList<Candle>();
		List<Negocio> negociosDoMesmoDia = new ArrayList<Negocio>();
		Calendar dataTemp = negocios.get(0).getData();

		for (Negocio negocio : negocios) {
			if (!isMesmoDia(dataTemp, negocio.getData())) {
				fechaCandle(candles, negociosDoMesmoDia, dataTemp);
				negociosDoMesmoDia.clear();
				dataTemp = negocio.getData();
			}
			negociosDoMesmoDia.add(negocio);
		}

		fechaCandle(candles, negociosDoMesmoDia, dataTemp);

		return candles;
	}

	private void fechaCandle(List<Candle> candles,
			List<Negocio> negociosDoMesmoDia, Calendar dataTemp) {
		candles.add(constroiCandleParaData(dataTemp, negociosDoMesmoDia));
	}
}
