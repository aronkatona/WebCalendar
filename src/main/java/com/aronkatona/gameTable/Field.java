package com.aronkatona.gameTable;

public class Field {

	private Boat boat;
	private Destroyed destroyed;
	private Empty empty;
	
	public Field(){
		this.boat = null;
		this.destroyed = null;
		this.empty = null;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	
	public Destroyed getDestroyed() {
		return destroyed;
	}

	public void setDestroyed(Destroyed destroyed) {
		this.destroyed = destroyed;
	}

	public Empty getEmpty() {
		return empty;
	}

	public void setEmpty(Empty empty) {
		this.empty = empty;
	}

	public void setFieldToNull(){
		this.boat = null;
	}
	
	@Override
	public String toString(){
		if(boat != null) return "boat";
		if(destroyed != null) return "destroyed";
		if(empty != null) return "empty";
		return null;
	}
	
	
	
}
