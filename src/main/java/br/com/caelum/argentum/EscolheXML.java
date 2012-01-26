package br.com.caelum.argentum;

import java.io.FileReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EscolheXML {
	public void escolher() {
		try {
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileFilter(new FileNameExtensionFilter("Apenas XML",
					"xml"));
			int retorno = fileChooser.showOpenDialog(null);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				FileReader reader = new FileReader(
						fileChooser.getSelectedFile());
				List<Negocio> negocios = new LeitorXML().carrega(reader);

				Negocio primeiroNegocio = negocios.get(0);
				String msg = "Primeiro negocio do dia: "
						+ primeiroNegocio.getPreco();
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EscolheXML().escolher();
	}
}
