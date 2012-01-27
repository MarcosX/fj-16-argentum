package br.com.caelum.argentum;

import java.util.Calendar;

public class CandleBuilder {
	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private Calendar data;

	public CandleBuilder abertura(double abertura) {
		this.abertura = abertura;
		return this;
	}

	public CandleBuilder fechamento(double fechamento) {
		this.fechamento = fechamento;
		return this;
	}

	public CandleBuilder minimo(double minimo) {
		this.minimo = minimo;
		return this;
	}

	public CandleBuilder maximo(double maximo) {
		this.maximo = maximo;
		return this;
	}

	public CandleBuilder volume(double volume) {
		this.volume = volume;
		return this;
	}

	public CandleBuilder data(Calendar data) {
		this.data = data;
		return this;
	}

	public Candle gerarCandle() {
		return new Candle(abertura, fechamento, minimo, maximo, volume,
				data);
	}

}
