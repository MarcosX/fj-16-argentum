package br.com.caelum.argentum;

import br.com.caelum.argentum.Indicador;

public class IndicadorFechamento implements Indicador {

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getFechamento();
	}

	@Override
	public String toString() {
		return "Indicador de Fechamento";
	}

}
