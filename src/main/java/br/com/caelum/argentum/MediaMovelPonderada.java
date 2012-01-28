package br.com.caelum.argentum;

public class MediaMovelPonderada implements Indicador {

	private int periodoEmDias;
	private Indicador indicador;

	public MediaMovelPonderada(Indicador indicador, int periodoEmDias) {
		this.periodoEmDias = periodoEmDias;
		this.indicador = indicador;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		int somaDosPesos = 0;
		int inicioDaIteracao = (posicao - (periodoEmDias - 1)) >= 0 ? posicao
				- (periodoEmDias - 1) : 0;
		for (int i = inicioDaIteracao, peso = 1; i <= posicao; i++, peso++) {
			soma += indicador.calcula(i, serie) * peso;
			somaDosPesos += peso;
		}
		return soma / somaDosPesos;
	}

	@Override
	public String toString() {
		return "Media Movel Ponderada";
	}
}
