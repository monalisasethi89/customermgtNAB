package com.nab.assignment.customermanagement.enums;

/**
 * @author Monalisa Sethi
 *
 */
import java.io.Serializable;

public enum MaritalStatus implements Serializable {
	SINGLE, MARRIED, DIVORCED;

	@Override
	public String toString() {
		switch (this) {
		case SINGLE:
			return "Single";
		case MARRIED:
			return "Married";
		case DIVORCED:
			return "Divorced";
		}
		return "";
	}
}
