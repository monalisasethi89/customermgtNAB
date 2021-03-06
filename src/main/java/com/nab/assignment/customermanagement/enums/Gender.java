package com.nab.assignment.customermanagement.enums;

/**
 * @author Monalisa Sethi
 *
 */
import java.io.Serializable;

public enum Gender implements Serializable {
	MALE, FEMALE;

	@Override
	public String toString() {
		switch (this) {
		case MALE:
			return "Male";
		case FEMALE:
			return "Female";
		}
		return "";
	}
}
