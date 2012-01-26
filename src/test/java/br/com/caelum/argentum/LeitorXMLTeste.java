package br.com.caelum.argentum;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

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

	@Test
	public void testaXMLVazio() {
		String xml = "";

		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));
		assertEquals(true, negocios.isEmpty());
	}

	@Test
	public void testaXMLComDadosFaltando() {
		String xml = "<list>" + "<negocio>" + "<quantidade>1000</quantidade>"
				+ "<data>" + "<time>555454646</time>" + "</data>"
				+ "</negocio>" + "</list>";

		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));
		assertEquals(0.0, negocios.get(0).getPreco(), 0.00001);
	}

	@Test
	public void testaListaNegocioParaXML() {
		LeitorXML leitor = new LeitorXML();
		Negocio n1 = new Negocio(100, 10, new GregorianCalendar(2008, 0, 1));

		String xml = leitor.listaDeNegocioParaXML(n1);
		List<Negocio> negocios = leitor.carrega(new StringReader(xml));

		assertEquals(1, negocios.size());

		Negocio n2 = negocios.get(0);

		assertEquals(n1.getPreco(), n2.getPreco(), 0.00001);
		assertEquals(n1.getQuantidade(), n2.getQuantidade(), 0.00001);
		assertEquals(n1.getData().getTimeInMillis(), n2.getData()
				.getTimeInMillis());
	}
}
