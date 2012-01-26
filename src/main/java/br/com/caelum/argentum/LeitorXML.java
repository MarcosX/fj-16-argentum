package br.com.caelum.argentum;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	@SuppressWarnings("unchecked")
	public List<Negocio> carrega(Reader reader) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		List<Negocio> negocios = Arrays.asList();
		try {
			negocios = (List<Negocio>) stream.fromXML(reader);
		} catch (StreamException e) {
			System.out.println("Arquivo mal formatado.");
		}

		return negocios;
	}

	public String listaDeNegocioParaXML(Negocio... n1) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		stream.alias("list", List.class);
		List<Negocio> negocios = Arrays.asList(n1);
		return stream.toXML(negocios);
	}
}
