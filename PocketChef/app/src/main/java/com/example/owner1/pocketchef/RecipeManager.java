package com.example.owner1.pocketchef;

import java.util.ArrayList;

public class RecipeManager {
	private ArrayList<Recipe> recipes;
	
	RecipeManager(){
		recipes = new ArrayList<Recipe>();
	}
	
	RecipeManager(ArrayList<Recipe> r){
		recipes = r;
	}
	
	ArrayList<Recipe> getRecipes(){ return recipes; }
	
	ArrayList<String> getRecipeNames(){
		ArrayList<String> names = new ArrayList<String>();
		
		for (int i = 0; i < recipes.size(); ++i){
			names.add(recipes.get(i).getName());
		}
		
		return names;
	}
	
	void addRecipe(Recipe r){
		recipes.add(r);
	}
	
	Recipe getRecipe(int index){
		return recipes.get(index);
	}
	
	//returns first instance of recipe with the given name
	Recipe getRecipe(String recipename){
		for (int i = 0; i < recipes.size(); ++i){
			if (recipes.get(i).getName().equals(recipename)){
				return recipes.get(i);
			}
		}
		
		System.out.println("Recipe requested for retrieval not found.");
		return null;
	}
	
	void removeRecipe(int index){
		recipes.remove(index);
	}
	
	//removes first instance of recipe with the given name
	void removeRecipe(String recipename){
		for (int i = 0; i < recipes.size(); ++i){
			if (recipes.get(i).getName().equals(recipename)){
				recipes.remove(i);
				return;
			}
		}
		
		System.out.println("Recipe requested for removal not found.");
		return;
	}
	
	//note: this filters out duplicates by name
	//this assumes we never care about the amounts when filtering
	//ONLY use this function for displaying purposes!
	ArrayList<Ingredient> getAllUniqueIngredients(){
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ArrayList<Ingredient> temp = new ArrayList<Ingredient>();
		
		for (int i = 0; i < recipes.size(); ++i){
			temp = recipes.get(i).getIngredients();
			
			for (int j = 0; j < temp.size(); ++j){
				if (!ingredients.contains(temp.get(j))){
					ingredients.add(temp.get(j));
				}
			}
		}
		
		return ingredients;
	}
	
	//begin filtering functions
	ArrayList<Recipe> filter(ArrayList<Ingredient> ingredients, double cost_min, double cost_max, double time_min, double time_max){
		ArrayList<Recipe> filtered_recipes = new ArrayList<Recipe>();
		double temp_cost = 0.0;
		double temp_time = 0.0;
		
		//filter by cost & time
		for (int i = 0; i < recipes.size(); ++i){
			temp_cost = recipes.get(i).getCost();
			temp_time = recipes.get(i).getTime();
			
			if ((cost_min < temp_cost) && (temp_cost < cost_max) && (time_min < temp_time) && (temp_time < time_max)){
				filtered_recipes.add(recipes.get(i));
			}
		}
		
		//if there are no ingredients do not complete the next step
		if (ingredients.size() == 0){
			return filtered_recipes;
		}
		
		//filter by ingredients
		//this does NOT take into account amounts of each ingredients
		//this is bubble sort right now, make this efficient later!
		Recipe temp;
		
		for (int i = 0; i < filtered_recipes.size(); ++i){
			for (int j = 1; j < filtered_recipes.size() - i; ++j){
				if (getIngCount(filtered_recipes.get(j - 1), ingredients) < getIngCount(filtered_recipes.get(j), ingredients)){
					temp = filtered_recipes.get(j);
					filtered_recipes.set(j, filtered_recipes.get(j - 1));
					filtered_recipes.set(j - 1, temp);
				}
			}
		}
		
		for (int i = 0; i < filtered_recipes.size(); ++i){
			if (getIngCount(filtered_recipes.get(i), ingredients) == 0){
				filtered_recipes.remove(i);
			}
		}
		
		return filtered_recipes;
	}
	
	//used in the filter function to get an ingredient priority
	//also not efficient
	int getIngCount(Recipe r, ArrayList<Ingredient> ingredients){
		int count = 0;
		ArrayList<String> recipeIngNames = r.getIngredientNames();
		
		for (int i = 0; i < ingredients.size(); ++i){
			if (recipeIngNames.contains(ingredients.get(i).getName())){
				String recipe_ing = recipeIngNames.get(recipeIngNames.indexOf(ingredients.get(i).getName()));
				count++;
			}
		}
		
		return count;
	}
}