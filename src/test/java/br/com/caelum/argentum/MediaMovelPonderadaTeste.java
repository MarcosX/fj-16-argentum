package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class MediaMovelPonderadaTeste {

	public SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

	@Test
	public void testaMediaPonderadaTresDias() {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 5, 6);
		MediaMovelPonderada mmp = new MediaMovelPonderada(
				new IndicadorFechamento(), 3);

		Assert.assertEquals(14d / 6, mmp.calcula(2, serie), 0.0001);
		Assert.assertEquals(20d / 6, mmp.calcula(3, serie), 0.0001);
		Assert.assertEquals(26d / 6, mmp.calcula(4, serie), 0.0001);
		Assert.assertEquals(32d / 6, mmp.calcula(5, serie), 0.0001);
	}

	@Test
	public void testaMediaPonderadaCincoDias() {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 5, 6);
		MediaMovelPonderada mmp = new MediaMovelPonderada(
				new IndicadorFechamento(), 5);

		Assert.assertEquals(55d / 15, mmp.calcula(4, serie), 0.0001);
		Assert.assertEquals(70d / 15, mmp.calcula(5, serie), 0.0001);
	}

	@Test
	public void testaMediaComAcessoInvalido() {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 5, 6);
		MediaMovelPonderada mmp = new MediaMovelPonderada(
				new IndicadorFechamento(), 3);

		Assert.assertEquals(5d / 3, mmp.calcula(1, serie), 0.0001);
		Assert.assertEquals(1d / 1, mmp.calcula(0, serie), 0.0001);

	}
}
