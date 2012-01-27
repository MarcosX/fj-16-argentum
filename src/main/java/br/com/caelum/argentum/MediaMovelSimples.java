package br.com.caelum.argentum;

public class MediaMovelSimples implements Indicador {

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		for (int i = posicao - 2; i <= posicao; i++) {
			Candle c = serie.getCandle(i);
			soma += c.getFechamento();
		}
		return soma / 3;
	}

	@Override
	public String toString() {
		return "Media Móvel Simples";
	}
}
