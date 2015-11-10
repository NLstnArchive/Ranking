package com.xAwesom3.ranking;

import java.util.ArrayList;
import java.util.List;

public class Pupil {

	private static List<Pupil>	pupils	= new ArrayList<Pupil>();

	private String				name, password;

	public Pupil(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public static void add(Pupil pupil) {
		pupils.add(pupil);
	}

	public static List<Pupil> getList() {
		return pupils;
	}

	public static Pupil getByName(String name) {
		for (Pupil pupil : pupils) {
			if (pupil.getName().equals(name))
				return pupil;
		}
		return null;
	}
}
