package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class MediaMovelSimplesTeste {

	public SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

	@Test
	public void testaExemploSimplesParaMedia() {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		Indicador mms = new MediaMovelSimples();

		Assert.assertEquals(2.0, mms.calcula(2, serie), 0.00001);
		Assert.assertEquals(3.0, mms.calcula(3, serie), 0.00001);
		Assert.assertEquals(10.0 / 3, mms.calcula(4, serie), 0.00001);
		Assert.assertEquals(11.0 / 3, mms.calcula(5, serie), 0.00001);
		Assert.assertEquals(4.0, mms.calcula(6, serie), 0.00001);
		Assert.assertEquals(13.0 / 3, mms.calcula(7, serie), 0.00001);
		Assert.assertEquals(4.0, mms.calcula(8, serie), 0.00001);
	}
}
