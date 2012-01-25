package br.com.caelum.argentum;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Candlestick {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;

	public Candlestick(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		if (maximo < minimo) {
			throw new IllegalArgumentException(
					"Maximo deve ser menor que Minimo");
		}
		if (abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0
				|| volume < 0) {
			throw new IllegalArgumentException(
					"Não é possível atribuir valores negativos");
		}
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser nulo");
		}

		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = (Calendar) data.clone();
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}

	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy");
		return "[Abertura " + this.abertura + ", Fechamento " + this.fechamento
				+ ", Mínima " + this.minimo + ", Maxima " + this.maximo
				+ ", Volume " + this.volume + ", Data "
				+ sdf.format(this.data.getTime()) + "]";
	}
}
