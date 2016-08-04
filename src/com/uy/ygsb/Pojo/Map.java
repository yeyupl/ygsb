package com.uy.ygsb.Pojo;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private int id, camp, minLevel, maxLevel, PreMap, nextMap;
	private String name, desc, skin;
	private List<Npc> npcs = new ArrayList<Npc>();
	private List<Monster> monsters = new ArrayList<Monster>();
	private List<Building> buildings = new ArrayList<Building>();

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}

	public int getCamp() {
		return camp;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setPreMap(int preMap) {
		PreMap = preMap;
	}

	public int getPreMap() {
		return PreMap;
	}

	public void setNextMap(int nextMap) {
		this.nextMap = nextMap;
	}

	public int getNextMap() {
		return nextMap;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setNpcs(List<Npc> npcs) {
		this.npcs = npcs;
	}

	public List<Npc> getNpcs() {
		return npcs;
	}

	public void setMonsters(List<Monster> monsters) {
		this.monsters = monsters;
	}

	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getSkin() {
		return skin;
	}
}
