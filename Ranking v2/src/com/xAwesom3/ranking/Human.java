package com.xAwesom3.ranking;

import java.util.ArrayList;
import java.util.List;

public class Human {

	private static List<Human>	men			= new ArrayList<Human>();
	private static List<Human>	women		= new ArrayList<Human>();
	private static List<Human>	teachers	= new ArrayList<Human>();

	public static Human getManByName(String name) {
		Human result = null;
		for (Human human : men) {
			if (human.getName().equals(name)) {
				result = human;
			}
		}
		return result;
	}

	public static Human getWomanByName(String name) {
		for (Human human : women) {
			if (human.getName().equalsIgnoreCase(name))
				return human;
		}
		return null;
	}

	public static Human getTeacherByName(String name) {
		for (Human human : teachers) {
			if (human.getName().equalsIgnoreCase(name))
				return human;
		}
		return null;
	}

	public static void add(Human human) {
		if (human.getGender() == 0)
			teachers.add(human);
		if (human.getGender() == 1)
			men.add(human);
		if (human.getGender() == 2)
			women.add(human);
	}

	public static List<Human> getTeachers() {
		return teachers;
	}

	public static List<Human> getMen() {
		return men;
	}

	public static List<Human> getWomen() {
		return women;
	}

	private String	name, password;
	private int		gender;

	public Human(String name, String password, int gender) {
		this.name = name;
		this.password = password;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getGender() {
		return gender;
	}
}
