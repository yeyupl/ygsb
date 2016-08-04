package com.uy.ygsb.Pojo;

public class Item {
	private int id, category, addNum, type, subType, typeId, num, sellCopper;
	private int timeLimit, buyCopper, buySilver, buyGold;
	private String name, desc, skin;
	private boolean sellAble, dropAble, buyAble;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setAddNum(int addNum) {
		this.addNum = addNum;
	}

	public int getAddNum() {
		return addNum;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getSubType() {
		return subType;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setSellAble(boolean sellAble) {
		this.sellAble = sellAble;
	}

	public boolean getSellAble() {
		return sellAble;
	}

	public void setDropAble(boolean dropAble) {
		this.dropAble = dropAble;
	}

	public boolean getDropAble() {
		return dropAble;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setBuyAble(boolean buyAble) {
		this.buyAble = buyAble;
	}

	public boolean getBuyAble() {
		return buyAble;
	}

	public void setBuyCopper(int buyCopper) {
		this.buyCopper = buyCopper;
	}

	public int getBuyCopper() {
		return buyCopper;
	}

	public void setBuySilver(int buySilver) {
		this.buySilver = buySilver;
	}

	public int getBuySilver() {
		return buySilver;
	}

	public void setBuyGold(int buyGold) {
		this.buyGold = buyGold;
	}

	public int getBuyGold() {
		return buyGold;
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

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCategory() {
		return category;
	}

	public void setSellCopper(int sellCopper) {
		this.sellCopper = sellCopper;
	}

	public int getSellCopper() {
		return sellCopper;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getSkin() {
		return skin;
	}
}
