package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

public class DateUtils {

	/**
	 * Checa se a data passada como parametro é uma data Atual ou Futura.
	 * Caso seja atual ou futura, retornará true
	 * @param date - LocalDate
	 * @return True para datas Atuais ou futuras
	 */
	public static boolean isEqualOrFutureDate(LocalDate date) {
		return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
	}
}
