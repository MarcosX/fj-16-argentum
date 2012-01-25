package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.io.StreamException;

public class LeitorXMLTeste {

	@Test
	public void testaLeitorXMLCarregaListaNegocios() {
		String xml = "<list>" + "<negocio>" + "<preco>40.0</preco>"
				+ "<quantidade>1000</quantidade>" + "<data>"
				+ "<time>555454646</time>" + "</data>" + "</negocio>"
				+ "<negocio>" + "<preco>30.0</preco>"
				+ "<quantidade>1000</quantidade>" + "<data>"
				+ "<time>555454646</time>" + "</data>" + "</negocio>"
				+ "</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));

		assertEquals(2, negocios.size());
		assertEquals(40, negocios.get(0).getPreco(), 0.00001);
		assertEquals(30, negocios.get(1).getPreco(), 0.00001);
	}

	@Test(expected = NullPointerException.class)
	public void testaXMLInvalido() {
		String xml = null;

		LeitorXML leitor = new LeitorXML();
		@SuppressWarnings("unused")
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));
	}

	@Test(expected = StreamException.class)
	public void testaXMLVazio() {
		String xml = "";

		LeitorXML leitor = new LeitorXML();
		@SuppressWarnings("unused")
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));
	}

	@Test
	public void testaXMLComDadosFaltando() {
		String xml = "<list>" + "<negocio>" + "<quantidade>1000</quantidade>"
				+ "<data>" + "<time>555454646</time>" + "</data>"
				+ "</negocio>" + "</list>";

		LeitorXML leitor = new LeitorXML();
		@SuppressWarnings("unused")
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));
		assertEquals(0.0, negocios.get(0).getPreco(), 0.00001);
	}
}
