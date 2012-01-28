package br.com.caelum.argentum;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;

import br.com.caelum.argentum.grafico.GeradorDeGrafico;

public class TestaGrafico {

	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 4, 3, 2,
				1, 2, 2, 4, 5, 6, 7, 8, 9, 10, 11, 10, 6, 3, 2, 6, 7, 8, 9);
		GeradorDeGrafico g = new GeradorDeGrafico(serie, 3, 32);
		g.criaGrafico("Media movel simples do fechamento das acoes");
		g.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento(), 3));
		g.plotaIndicador(new MediaMovelPonderada(new IndicadorFechamento(), 3));
		g.plotaIndicador(new IndicadorFechamento());
		g.salvar(new FileOutputStream("grafico.png"));

		JFrame frame = new JFrame("Meu gráfico");
		frame.add(g.getPanel());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
