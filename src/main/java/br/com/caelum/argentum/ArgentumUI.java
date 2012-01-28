package br.com.caelum.argentum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.ui.ReflectionTableModel;

public class ArgentumUI {

	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	private JPanel painelBotoes;
	private JTabbedPane abas;
	private JFormattedTextField campoDataInicio;

	public static void main(String[] args) {
		new ArgentumUI().montaTela();
	}

	public void montaTela() {
		montaJanela();
		montaPainelPrincipal();
		montaAbas();
		montaTitulo();
		montaTabelaComScroll();
		montaPainelBotoes();
		montaCampoData();
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}

	private void montaCampoData() {
		JLabel dataLabel = new JLabel("Data de filtragem:");
		painelBotoes.add(dataLabel);
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');

			campoDataInicio = new JFormattedTextField(mascara);
			painelBotoes.add(campoDataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void montaAbas() {
		abas = new JTabbedPane();
		abas.addTab("Tabela de Negócios", null);
		abas.addTab("Gráficos", null);
		painelPrincipal.add(abas, BorderLayout.CENTER);
	}

	private void montaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void montaTitulo() {
		JLabel titulo = new JLabel("Lista de Negocios");
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		titulo.setForeground(new Color(50, 50, 100));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		painelPrincipal.add(titulo, BorderLayout.NORTH);
	}

	private void montaTabelaComScroll() {
		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.BLACK));
		tabela.setGridColor(Color.BLACK);
		tabela.setShowGrid(true);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);

		abas.setComponentAt(0, scroll);
	}

	private void montaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janela.add(painelPrincipal);
	}

	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				carregarDados();
			}
		});
		painelBotoes.add(botaoCarregar);
	}

	private void carregarDados() {
		List<Negocio> negocios = new EscolheXML().escolher();
		filtrarPorData(negocios);
		Collections.sort(negocios);

		ReflectionTableModel model = new ReflectionTableModel(negocios);
		tabela.setModel(model);

		CandlestickFactory candleFactory = new CandlestickFactory();
		List<Candle> candles = candleFactory.constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, 2,
				serie.getTotal() - 1);
		geradorDeGrafico.criaGrafico("Média Movel Simples");
		geradorDeGrafico.plotaIndicador(new MediaMovelSimples(
				new IndicadorFechamento(), 3));
		JPanel grafico = geradorDeGrafico.getPanel();
		this.abas.setComponentAt(1, grafico);
	}

	private void filtrarPorData(List<Negocio> negocios) {
		try {
			String valor = (String) campoDataInicio.getValue();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			Date dataInicio = formato.parse(valor);

			Iterator<Negocio> it = negocios.iterator();
			while (it.hasNext()) {
				if (it.next().getData().getTime().before(dataInicio)) {
					it.remove();
				}
			}
		} catch (Exception e) {
			campoDataInicio.setValue(null);
		}
	}

	private void montaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelBotoes.add(botaoSair);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
		janela.setLocationRelativeTo(null);
	}

}
