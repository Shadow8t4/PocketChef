package com.example.owner1.pocketchef;

import java.util.ArrayList;

public class Recipe {
	private String recipename;
	private ArrayList<Ingredient> ingredients;
	private String appliance;
	private double cost;
	private double time;
	private String url;
	private ArrayList<String> text;

	Recipe(){
		recipename = "";
		ingredients = new ArrayList<Ingredient>();
		appliance = "";
		cost = 0.0;
		time = 0.0;
		url = "";
		text = new ArrayList<String>();
	}

	Recipe(String n, ArrayList<Ingredient> i, String a, double t, String u, ArrayList<String> txt){
		recipename = n;
		ingredients = i;
		appliance = a;
		calculateCost();
		time = t;
		url = u;
		text = txt;
	}

	void calculateCost(){
		cost = 0;

		for (int i = 0; i < ingredients.size(); ++i){
			cost += ingredients.get(i).getTotalCost();
		}
	}

	String getName(){ return recipename; }
	void setName(String n){ recipename = n; }
	ArrayList<Ingredient> getIngredients(){ return ingredients; }
	void setIngredients(ArrayList<Ingredient> i){ ingredients = i; }
	String getAppliance(){ return appliance; }
	void setAppliance(String a){ appliance = a; }
	double getCost(){ return cost; }
	void setCost(double c){ calculateCost(); }
	double getTime(){ return time; }
	void setTime(double t){ time  = t; }
	void setURL(String u){ url  = u; }
	String getURL(){ return url; }
	ArrayList<String> getText(){ return text; }
	void setText(ArrayList<String> txt){text = txt; }

	ArrayList<String> getIngredientNames(){
		ArrayList<String> names = new ArrayList<String>();

		for (int i = 0; i < ingredients.size(); ++i){
			names.add(ingredients.get(i).getName());
		}
		return names;
	}
	public String toString(){
		String output = "";
		output += recipename + "\n";
		
		for (int i = 0; i < ingredients.size(); ++i){
			output += ingredients.get(i).toString() + "\n";
		}
		
		output += appliance + "\n" + cost + "\n" + time + "\n";
		
		for (int i = 0; i < text.size(); ++i){
			output += text.get(i) + "\n";
		}
		
		return output;
	}

    void addIngredient(Ingredient ing){
        ingredients.add(ing);
    }
}
