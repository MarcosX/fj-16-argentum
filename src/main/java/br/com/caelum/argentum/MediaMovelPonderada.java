package br.com.caelum.argentum;

public class MediaMovelPonderada implements Indicador {

	private int periodoEmDias;

	public MediaMovelPonderada(int periodoEmDias) {
		this.periodoEmDias = periodoEmDias;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		for (int i = posicao - (periodoEmDias - 1), peso = 1; i <= posicao; i++, peso++) {
			Candle c = serie.getCandle(i);
			soma += c.getFechamento() * peso;
		}
		return soma / 6;
	}

	@Override
	public String toString() {
		return "Media Movel Ponderada";
	}
}
