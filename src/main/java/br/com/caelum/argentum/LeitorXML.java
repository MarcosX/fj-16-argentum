package br.com.caelum.argentum;

import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	@SuppressWarnings("unchecked")
	public List<Negocio> carrega(Reader reader) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		return (List<Negocio>) stream.fromXML(reader);
	}
}
