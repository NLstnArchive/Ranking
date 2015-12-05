package com.xAwesom3.ranking;

import java.util.ArrayList;
import java.util.List;

public class Human {

	private static List<Human>	men				= new ArrayList<Human>();
	private static List<Human>	women			= new ArrayList<Human>();
	private static List<Human>	menTeachers		= new ArrayList<Human>();
	private static List<Human>	womenTeachers	= new ArrayList<Human>();

	private static Human		currentHuman;

	public static void setUser(Human human) {
		currentHuman = human;
	}

	public static Human getUser() {
		return currentHuman;
	}

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

	public static Human getTeacherManByName(String name) {
		for (Human human : menTeachers) {
			if (human.getName().equalsIgnoreCase(name))
				return human;
		}
		return null;
	}

	public static Human getTeacherWomanByName(String name) {
		for (Human human : womenTeachers) {
			if (human.getName().equalsIgnoreCase(name))
				return human;
		}
		return null;
	}

	public static List<Human> getByID(int id) {
		if (id == 1)
			return men;
		if (id == 2)
			return women;
		if (id == 3)
			return menTeachers;
		if (id == 4)
			return womenTeachers;
		else
			return null;
	}

	public static void add(Human human) {
		if (human.getGender() == 3)
			menTeachers.add(human);
		if (human.getGender() == 4)
			womenTeachers.add(human);
		if (human.getGender() == 1)
			men.add(human);
		if (human.getGender() == 2)
			women.add(human);
	}

	public static List<Human> getMenTeachers() {
		return menTeachers;
	}

	public static List<Human> getWomenTeachers() {
		return womenTeachers;
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
