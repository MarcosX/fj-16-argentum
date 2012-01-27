package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class SerieTemporalTeste {

	public SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle(d, d, d, d, 1000, Calendar
					.getInstance()));
		}
		return new SerieTemporal(candles);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testaIndiceErrado() {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		serie.getCandle(100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testaSerieListaNula() {
		@SuppressWarnings("unused")
		SerieTemporal serieTemporal = new SerieTemporal(null);
	}
}
