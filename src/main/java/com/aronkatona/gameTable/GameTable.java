package com.aronkatona.gameTable;

import com.aronkatona.model.User;

public class GameTable {
	
	private User user;
	private User opponent;
	
	private int hp;
	
	private Field[][] gameTable;

	private final int SIZE = 10;
	
	public GameTable(){
		hp = 5;
		gameTable = new Field[SIZE][SIZE];
		for(int i = 0; i < SIZE; ++i){
			for(int j = 0; j < SIZE; ++j){
				gameTable[i][j] = new Field();
				gameTable[i][j].setEmpty(new Empty());
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Field[][] getGameTable() {
		return gameTable;
	}

	public void setGameTable(Field[][] gameTableMe) {
		this.gameTable = gameTableMe;
	}

	public Field getFieldAt(int i , int j){
		return gameTable[i][j];
	}
	
	public User getOpponent() {
		return opponent;
	}

	public void setOpponent(User opponent) {
		this.opponent = opponent;
	}
	
	public void clearTable(){
		for(int i = 0; i < SIZE; ++i){
			for(int j = 0; j < SIZE; ++j){
				gameTable[i][j].setFieldToNull();
			}
		}
		
		setOpponent(null);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void minusHp(){
		this.hp-=1;
	}

}
