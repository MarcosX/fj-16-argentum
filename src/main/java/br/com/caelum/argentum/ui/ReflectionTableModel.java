package br.com.caelum.argentum.ui;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ReflectionTableModel extends AbstractTableModel {

	private List<?> lista;
	private Class<?> classe;

	public ReflectionTableModel(List<?> list) {
		this.lista = list;
		this.classe = list.get(0).getClass();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		int colunas = 0;
		for (Method m : classe.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Coluna.class)) {
				colunas++;
			}
		}
		return colunas;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			Object o = lista.get(rowIndex);
			for (Method m : classe.getDeclaredMethods()) {
				Coluna c = m.getAnnotation(Coluna.class);
				if (c != null && c.posicao() == columnIndex) {
					return String.format(c.formato(), m.invoke(o));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		for (Method m : classe.getDeclaredMethods()) {
			Coluna c = m.getAnnotation(Coluna.class);
			if (c != null && c.posicao() == column) {
				return c.nome();
			}
		}
		return null;
	}
}
