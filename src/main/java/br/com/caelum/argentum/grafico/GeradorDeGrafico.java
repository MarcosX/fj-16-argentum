package br.com.caelum.argentum.grafico;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.caelum.argentum.Indicador;
import br.com.caelum.argentum.SerieTemporal;

public class GeradorDeGrafico {
	private final SerieTemporal serie;
	private final int comeco;
	private final int fim;

	private DefaultCategoryDataset dataSet;
	private JFreeChart grafico;

	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		super();
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
	}

	public void criaGrafico(String titulo) {
		this.dataSet = new DefaultCategoryDataset();
		this.grafico = ChartFactory.createLineChart(titulo, "Dias", "Valores",
				dataSet, PlotOrientation.VERTICAL, true, true, false);
	}

	public void plotaIndicador(Indicador indicador) {
		for (int i = comeco; i < fim; i++) {
			double valor = indicador.calcula(i, serie);
			dataSet.addValue(valor, indicador.toString(), "" + i);
		}
	}

	public void salvar(OutputStream out) throws IOException {
		ChartUtilities.writeChartAsPNG(out, grafico, 500, 350);
	}

	public JPanel getPanel() {
		return new ChartPanel(grafico);
	}
}
