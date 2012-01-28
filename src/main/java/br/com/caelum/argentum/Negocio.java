package br.com.caelum.argentum;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.caelum.argentum.ui.Coluna;

public final class Negocio implements Comparable<Negocio> {
	private final double preco;
	private final int quantidade;
	private final Calendar data;

	public Negocio(double preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	@Coluna(posicao = 0, nome = "Preço", formato = "R$ %.2f")
	public double getPreco() {
		return preco;
	}

	@Coluna(posicao = 1, nome = "Quantidade")
	public int getQuantidade() {
		return quantidade;
	}

	@Coluna(posicao = 2, nome = "Data", formato = "%1$Td/%1$Tm/%1$TY")
	public Calendar getData() {
		return (Calendar) data.clone();
	}

	@Coluna(posicao = 3, nome = "Volume", formato = "R$ %,#.2f")
	public double getVolume() {
		return preco * quantidade;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy");
		return "[Quantidade " + this.quantidade + ", Preco " + this.preco
				+ ", Data " + sdf.format(this.data.getTime()) + "]";
	}

	@Override
	public int compareTo(Negocio o) {
		Calendar data1 = getData();
		Calendar data2 = o.getData();
		if (data1.get(Calendar.DAY_OF_MONTH) == data2
				.get(Calendar.DAY_OF_MONTH)
				&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
				&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR)) {
			return 0;
		} else if (data1.get(Calendar.YEAR) > data2.get(Calendar.YEAR)
				|| data1.get(Calendar.MONTH) > data2.get(Calendar.MONTH)
				|| data1.get(Calendar.DAY_OF_MONTH) > data2
						.get(Calendar.DAY_OF_MONTH)) {
			return 1;
		}
		return -1;
	}
}
