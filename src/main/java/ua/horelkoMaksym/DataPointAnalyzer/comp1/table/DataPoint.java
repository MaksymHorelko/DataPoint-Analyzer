package ua.horelkoMaksym.DataPointAnalyzer.comp1.table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ua.horelkoMaksym.DataPointAnalyzer.comp1.exceptions.DateParsingException;

public class DataPoint implements Serializable {

	private static final long serialVersionUID = -4757043582380073320L;

	double x;
	double y;
	String date;

	public DataPoint() {
		x = 0;
		y = 0;
		date = "";
	}

	public DataPoint(double x, double y, String date) {
		this.x = x;
		this.y = y;
		setDate(date);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getDate() {
		return date;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setDate(String date) {
		if (!isDateParseable(date)) {
			throw new DateParsingException("Date '" + date + "' could not be parsed");
		}
		this.date = date;
	}

	public static boolean isDateParseable(String inputDate) {
		try {
			LocalDate.parse(inputDate);
			return true;
		}

		catch (DateTimeParseException e) {
			return false;
		}
	}

}
