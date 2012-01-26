package br.com.caelum.argentum;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void testeSimplesSequenciaNegocios() {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);
		Negocio n2 = new Negocio(45.0, 100, hoje);
		Negocio n3 = new Negocio(39.8, 100, hoje);
		Negocio n4 = new Negocio(42.3, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void testeSemNegocios() {
		Calendar hoje = Calendar.getInstance();

		List<Negocio> negocios = Arrays.asList();

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		Assert.assertEquals(0.0, candle.getAbertura(), 0.00001);
		Assert.assertEquals(0.0, candle.getFechamento(), 0.00001);
		Assert.assertEquals(0.0, candle.getMinimo(), 0.00001);
		Assert.assertEquals(0.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(0.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void testeComUmNegocio() {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);
		List<Negocio> negocios = Arrays.asList(n1);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candle.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candle.getMinimo(), 0.00001);
		Assert.assertEquals(40.5, candle.getMaximo(), 0.00001);
		Assert.assertEquals(4050.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void testaNegociosEmOrdemCrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(39.8, 100, hoje);
		Negocio n2 = new Negocio(40.5, 100, hoje);
		Negocio n3 = new Negocio(42.3, 100, hoje);
		Negocio n4 = new Negocio(45.0, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		Assert.assertEquals(39.8, candle.getAbertura(), 0.00001);
		Assert.assertEquals(45.0, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void testaNegociosEmOrdemDecrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(45.0, 100, hoje);
		Negocio n2 = new Negocio(42.3, 100, hoje);
		Negocio n3 = new Negocio(40.5, 100, hoje);
		Negocio n4 = new Negocio(39.8, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		Assert.assertEquals(45.0, candle.getAbertura(), 0.00001);
		Assert.assertEquals(39.8, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void testaComparaMesmoDia() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		assertTrue(fabrica.isMesmoDia(data1, data2));
	}

	@Test
	public void testaComparaMesmoDiaHorasDiferentes() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar data1 = new GregorianCalendar(2012, 0, 25, 8, 30, 0);
		Calendar data2 = new GregorianCalendar(2012, 0, 25, 9, 30, 0);
		assertTrue(fabrica.isMesmoDia(data1, data2));
	}

	@Test
	public void testaConstruirCandleDeNegociosComDatasDiferentes() {
		Calendar hoje = Calendar.getInstance();

		Negocio n1 = new Negocio(45.0, 100, hoje);
		Negocio n2 = new Negocio(42.3, 100, hoje);
		Negocio n3 = new Negocio(40.5, 100, hoje);
		Negocio n4 = new Negocio(39.8, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negocio n5 = new Negocio(48.8, 100, amanha);
		Negocio n6 = new Negocio(49.3, 100, amanha);

		Calendar depoisDeAmanha = (Calendar) amanha.clone();
		depoisDeAmanha.add(Calendar.DAY_OF_MONTH, 1);

		Negocio n7 = new Negocio(51.8, 100, depoisDeAmanha);
		Negocio n8 = new Negocio(52.3, 100, depoisDeAmanha);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);

		CandlestickFactory fabrica = new CandlestickFactory();

		List<Candlestick> candles = fabrica.constroiCandles(negocios);

		assertEquals(3, candles.size());
		assertEquals(45.0, candles.get(0).getAbertura(), 0.00001);
		assertEquals(39.8, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
	}

	@Test
	public void testaConstruirCandlesComNegociosForaDaOrdem() {
		Calendar hoje = Calendar.getInstance();

		Negocio n1 = new Negocio(45.0, 100, hoje);
		Negocio n2 = new Negocio(42.3, 100, hoje);
		Negocio n3 = new Negocio(40.5, 100, hoje);
		Negocio n4 = new Negocio(39.8, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negocio n5 = new Negocio(48.8, 100, amanha);
		Negocio n6 = new Negocio(49.3, 100, amanha);

		Calendar depoisDeAmanha = (Calendar) amanha.clone();
		depoisDeAmanha.add(Calendar.DAY_OF_MONTH, 1);

		Negocio n7 = new Negocio(51.8, 100, depoisDeAmanha);
		Negocio n8 = new Negocio(52.3, 100, depoisDeAmanha);

		List<Negocio> negocios = Arrays.asList(n7, n1, n2, n3, n8, n5, n6, n4);

		CandlestickFactory fabrica = new CandlestickFactory();

		List<Candlestick> candles = fabrica.constroiCandles(negocios);

		assertEquals(3, candles.size());
		assertEquals(45.0, candles.get(0).getAbertura(), 0.00001);
		assertEquals(39.8, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
	}

}
