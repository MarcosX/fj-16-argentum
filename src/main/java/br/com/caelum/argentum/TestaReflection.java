package br.com.caelum.argentum;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;

public class TestaReflection {

	public static void main(String[] args) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Negocio n = new Negocio(10, 100, Calendar.getInstance());
		System.out.println("=== Atributos ===");
		Field[] campos = n.getClass().getDeclaredFields();
		for (Field field : campos) {
			System.out.println(field.getName());
		}
		System.out.println("\n===Métodos ===");
		Method[] metodos = n.getClass().getDeclaredMethods();
		for (Method method : metodos) {
			System.out.println(method.getName());
		}

		Field f = Negocio.class.getDeclaredField("preco");
		f.setAccessible(true);
		System.out.println(n.getPreco());
		f.set(n, 35.0);
		System.out.println(n.getPreco());
	}

}
