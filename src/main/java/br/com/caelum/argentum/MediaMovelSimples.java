package br.com.caelum.argentum;

public class MediaMovelSimples implements Indicador {

	private int periodoEmDias;
	private Indicador indicador;

	public MediaMovelSimples(Indicador indicador, int periodoEmDias) {
		this.indicador = indicador;
		this.periodoEmDias = periodoEmDias;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		int inicioIteracao = (posicao - (periodoEmDias - 1)) >= 0 ? posicao
				- (periodoEmDias - 1) : 0;
		int totalDeElementos = 0;
		for (int i = inicioIteracao; i <= posicao; i++, totalDeElementos++) {
			soma += indicador.calcula(i, serie);
		}
		return soma / totalDeElementos;
	}

	@Override
	public String toString() {
		return "Media Móvel Simples";
	}
}
