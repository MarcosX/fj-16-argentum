package br.com.caelum.argentum;

import java.util.List;

public class SerieTemporal {
	private final List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {
		if (candles == null) {
			throw new IllegalArgumentException(
					"Lista de Candles não pode ser nula");
		}
		this.candles = candles;
	}

	public Candle getCandle(int i) {
		return this.candles.get(i);
	}

	public int getTotal() {
		return this.candles.size();
	}
}
