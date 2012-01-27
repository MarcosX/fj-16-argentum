package br.com.caelum.argentum;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EscolheXML {
	public List<Negocio> escolher() {
		try {
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileFilter(new FileNameExtensionFilter("Apenas XML",
					"xml"));
			int retorno = fileChooser.showOpenDialog(null);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				FileReader reader = new FileReader(
						fileChooser.getSelectedFile());
				return new LeitorXML().carrega(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static void main(String[] args) {
		new EscolheXML().escolher();
	}
}
