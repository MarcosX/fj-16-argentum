package br.com.caelum.argentum;

import java.util.Calendar;

import static org.junit.Assert.*;
import org.junit.Test;

public class CandleStickTeste {

	@Test(expected = IllegalArgumentException.class)
	public void testePrecoMAximoNaoPodeSerMenorQueMinimo() {
		@SuppressWarnings("unused")
		Candlestick candle = new Candlestick(10, 20, 20, 10, 10000,
				Calendar.getInstance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testaValoresNegativos() {
		@SuppressWarnings("unused")
		Candlestick candle = new Candlestick(-10, -10, -10, -10, -10,
				Calendar.getInstance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testaDataNula() {
		@SuppressWarnings("unused")
		Candlestick candlestick = new Candlestick(10, 10, 10, 10, 10, null);
	}

	@Test
	public void testaSeEhAltaQndAberturaIgualFechamento() {
		Candlestick candlestick = new Candlestick(10, 10, 10, 10, 10,
				Calendar.getInstance());
		assertEquals(true, candlestick.isAlta());
	}
}
