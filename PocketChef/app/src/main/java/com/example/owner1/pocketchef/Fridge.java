package com.example.owner1.pocketchef;

import java.util.ArrayList;

public class Fridge {
	private ArrayList<Ingredient> ingredients;

	Fridge(){
		ingredients = new ArrayList<Ingredient>();
	}

	Fridge(ArrayList<Ingredient> i){
		ingredients = i;
	}

	ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	void setIngredients(ArrayList<Ingredient> i) {
		ingredients = i;
	}

	int getSize() {
		return ingredients.size();
	}

	void addIngredient(Ingredient i){
		ingredients.add(i);
	}

	Ingredient getIngredient(int i){
		return ingredients.get(i);
	}

	void removeIngredient(int i){
		ingredients.remove(i);
	}

	void removeIngredient(Ingredient ing){
		ingredients.remove(ing);
	}
}