/*
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Tests {

	@Test
	public void defaultNameShouldReturnBlank() {
		//creating ingredient
		Ingredient test = new Ingredient();
		String equality = "";

		assertEquals(equality, test.getName());
	}
	
	@Test
	public void givenNameShouldReturnGiven() {
		//creating ingredient
		Ingredient test = new Ingredient("Sugar", "teaspoon", 0.0, 0.0);
		String equality = "Sugar";

		assertEquals(equality, test.getName());
	}
	
	@Test
	public void getIngredientFromFridge() {
		//creating ingredient
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient("Sugar", "teaspoon", 1.01, 1.0));
		ingredients.add(new Ingredient("Salt", "teaspoon", 0.12, 100.0));
		ingredients.add(new Ingredient("Mustard", "teaspoon", 2.05, 2.0));
		
		Fridge test = new Fridge(ingredients);
		
		assertEquals(ingredients.get(0), test.getIngredient(0));
	}
	
	@Test
	public void setIngredientName() {
		//creating ingredient
		Ingredient test = new Ingredient("Sugar", "teaspoon", 0.0, 0.0);
		String equality = "Salt";
		test.setName(equality);
		
		assertEquals(equality, test.getName());
	}
	
	@Test
	public void testAllSetFunctionsIfEqualToOther() {
		//creating ingredient
		Ingredient test = new Ingredient("Sugar", "teaspoon", 0.0, 0.0);
		Ingredient test1 = new Ingredient("Salt", "Tablespoon", 1.5, 2.0);
		test.setName("Salt");
		test.setUnit("Tablespoon");
		test.setCost(1.5);
		test.setQuantity(2.0);
		
		assertEquals(test.getName(), test1.getName());
		assertEquals(test.getUnit(), test1.getUnit());
	}
	
	//***Tests for the Fridge***
	
	@Test
	public void removeIngredientFromFridge() {
		ArrayList<Ingredient> fridgeIngredients1 = new ArrayList<Ingredient>();
		fridgeIngredients1.add(new Ingredient("Sugar", "teaspoon", 1.01, 1.0));
		fridgeIngredients1.add(new Ingredient("Salt", "teaspoon", 0.12, 100.0));
		fridgeIngredients1.add(new Ingredient("Mustard", "teaspoon", 2.05, 2.0));
		fridgeIngredients1.add(new Ingredient("Ketchup", "gallon", 4.55, 1.0));
		
		
		ArrayList<Ingredient> fridgeIngredients2 = new ArrayList<Ingredient>();
		fridgeIngredients2.add(new Ingredient("Sugar", "teaspoon", 1.01, 1.0));
		fridgeIngredients2.add(new Ingredient("Salt", "teaspoon", 0.12, 100.0));
		fridgeIngredients2.add(new Ingredient("Mustard", "teaspoon", 2.05, 2.0));
		
		Fridge fridge1 = new Fridge(fridgeIngredients1);
		Fridge fridge2 = new Fridge(fridgeIngredients2);
		
		fridge1.removeIngredient(3); //Deletes "Ketchup from fridge 1"
		
		assertEquals(fridge1.getIngredient(0).getName(), fridge2.getIngredient(0).getName());
		assertEquals(fridge1.getIngredient(1).getName(), fridge2.getIngredient(1).getName());
		assertEquals(fridge1.getIngredient(2).getName(), fridge2.getIngredient(2).getName());
	}
	
	@Test
	public void getRecipeManagerRecipeNames() {
		
			//Tested here is:
				//addRecipe
				//getRecipe
				//constructor
				
		
		
		//Recipe 1
		ArrayList<Ingredient> recipeIngredients1 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps1 = new ArrayList<String>();
		
		recipeIngredients1.add(new Ingredient("Eggs", "egg", 2.0, 2.0));
		recipeIngredients1.add(new Ingredient("Cheese", "slice", 1.5, 2.0));
		recipeIngredients1.add(new Ingredient("Bread", "slice", 0.50, 2.0));
		recipeSteps1.add("1. Toss eggs into pan heated to medium-high heat");
		recipeSteps1.add("2. Stir occasionally for no more than 4-5 minutes");
		recipeSteps1.add("3. Put bread in toaster");
		recipeSteps1.add("4. Seriously it's an egg sandwich its not that hard");
		
		Recipe recipe1 = new Recipe("Egg Sandwich", recipeIngredients1, "Stove", 10.0, "url", recipeSteps1);
		
		//Recipe 2
		ArrayList<Ingredient> recipeIngredients2 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps2 = new ArrayList<String>();
		
		recipeIngredients2.add(new Ingredient("Pizza Sauce", "tablespoon", 2.0, 2.0));
		recipeIngredients2.add(new Ingredient("Pizza Crust", "slice", 1.50, 1.0));
		recipeIngredients2.add(new Ingredient("Shredded Cheese", "cup", 0.55, 1.25));
		recipeIngredients2.add(new Ingredient("Pepperoni", "piece", 2.50, 2.0));
		recipeSteps2.add("1. Spread sauce evenly across pizza");
		recipeSteps2.add("2. Sprinkle the shredded cheese across the pizza");
		recipeSteps2.add("3. Place pieces of pepperoni on the pizza");
		recipeSteps2.add("4. Place pizza in oven for about 5-6 minutes");
		recipeSteps2.add("5. Remove from oven, let sit and cool for a minute, slice and serve");
		
		Recipe recipe2 = new Recipe("Homemade Pizza", recipeIngredients2, "Oven", 15.0, "URL GOES HERE", recipeSteps2);
		
		//Recipe 3
		ArrayList<Ingredient> recipeIngredients3 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps3 = new ArrayList<String>();
		
		recipeIngredients3.add(new Ingredient("Tenderloin Steak", "ounce", 8.5, 6.4));
		recipeIngredients3.add(new Ingredient("Bacon", "strip", 1.25, 1.0));
		recipeIngredients3.add(new Ingredient("Salt", "teaspoon", 0.05, 1.25));
		recipeIngredients3.add(new Ingredient("Olive Oil", "teaspoon", 1.35, 1.0));
		recipeIngredients3.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		recipeSteps3.add("1. Season your steak with salt and pepper");
		recipeSteps3.add("2. Apply olive oil and rub oil and seasoning into the steak");
		recipeSteps3.add("3. Wrap a strip around the tenderloin");
		recipeSteps3.add("4. Heat a pan to medium-high heat, use either butter or olive oil to avoid sticking");
		recipeSteps3.add("5. Gently place tenderloin in center of pan. Sear 6 minutes each side to desired doneness");
		recipeSteps3.add("6. Once cooked to your preference, let it sit and cool for a minute, serve and enjoy");
		
		Recipe recipe3 = new Recipe("Bacon-Wrapped Tenderloin", recipeIngredients3, "Stove", 25.0, "URL GOES HERE", recipeSteps3);
		
		//Recipe List and Manager
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		recipeList.add(recipe1);
		recipeList.add(recipe2);
		recipeList.add(recipe3);
		
		RecipeManager recipeManager = new RecipeManager(recipeList);
		
		String solution0 = "Egg Sandwich";
		String solution1 = "Homemade Pizza";
		String solution2 = "Bacon-Wrapped Tenderloin";
		
		
		assertEquals(recipeManager.getRecipe(0).getName(), solution0);
		assertEquals(recipeManager.getRecipe(1).getName(), solution1);
		assertEquals(recipeManager.getRecipe(2).getName(), solution2);
	}
	
	@Test
	public void getRecipeManagerRecipeNamesAfterRemovingRecipe() {
		
			//Tested here is:
				//addRecipe
				//getRecipe
				//removeRecipe
				//constructor
		
		
		//Recipe 1
		ArrayList<Ingredient> recipeIngredients1 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps1 = new ArrayList<String>();
		
		recipeIngredients1.add(new Ingredient("Eggs", "egg", 2.0, 2.0));
		recipeIngredients1.add(new Ingredient("Cheese", "slice", 1.5, 2.0));
		recipeIngredients1.add(new Ingredient("Bread", "slice", 0.50, 2.0));
		recipeSteps1.add("1. Toss eggs into pan heated to medium-high heat");
		recipeSteps1.add("2. Stir occasionally for no more than 4-5 minutes");
		recipeSteps1.add("3. Put bread in toaster");
		recipeSteps1.add("4. Seriously it's an egg sandwich its not that hard");
		
		Recipe recipe1 = new Recipe("Egg Sandwich", recipeIngredients1, "Stove", 10.0, "URL GOES HERE", recipeSteps1);
		
		//Recipe 2
		ArrayList<Ingredient> recipeIngredients2 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps2 = new ArrayList<String>();
		
		recipeIngredients2.add(new Ingredient("Pizza Sauce", "tablespoon", 2.0, 2.0));
		recipeIngredients2.add(new Ingredient("Pizza Crust", "slice", 1.50, 1.0));
		recipeIngredients2.add(new Ingredient("Shredded Cheese", "cup", 0.55, 1.25));
		recipeIngredients2.add(new Ingredient("Pepperoni", "piece", 2.50, 2.0));
		recipeSteps2.add("1. Spread sauce evenly across pizza");
		recipeSteps2.add("2. Sprinkle the shredded cheese across the pizza");
		recipeSteps2.add("3. Place pieces of pepperoni on the pizza");
		recipeSteps2.add("4. Place pizza in oven for about 5-6 minutes");
		recipeSteps2.add("5. Remove from oven, let sit and cool for a minute, slice and serve");
		
		Recipe recipe2 = new Recipe("Homemade Pizza", recipeIngredients2, "Oven", 15.0, "URL GOES HERE", recipeSteps2);
		
		//Recipe 3
		ArrayList<Ingredient> recipeIngredients3 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps3 = new ArrayList<String>();
		
		recipeIngredients3.add(new Ingredient("Tenderloin Steak", "ounce", 8.5, 6.4));
		recipeIngredients3.add(new Ingredient("Bacon", "strip", 1.25, 1.0));
		recipeIngredients3.add(new Ingredient("Salt", "teaspoon", 0.05, 1.25));
		recipeIngredients3.add(new Ingredient("Olive Oil", "teaspoon", 1.35, 1.0));
		recipeIngredients3.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		recipeSteps3.add("1. Season your steak with salt and pepper");
		recipeSteps3.add("2. Apply olive oil and rub oil and seasoning into the steak");
		recipeSteps3.add("3. Wrap a strip around the tenderloin");
		recipeSteps3.add("4. Heat a pan to medium-high heat, use either butter or olive oil to avoid sticking");
		recipeSteps3.add("5. Gently place tenderloin in center of pan. Sear 6 minutes each side to desired doneness");
		recipeSteps3.add("6. Once cooked to your preference, let it sit and cool for a minute, serve and enjoy");
		
		Recipe recipe3 = new Recipe("Bacon-Wrapped Tenderloin", recipeIngredients3, "Stove", 25.0, "URL GOES HERE", recipeSteps3);
		
		//Recipe List and Manager
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		recipeList.add(recipe1);
		recipeList.add(recipe2);
		recipeList.add(recipe3);
		
		RecipeManager recipeManager = new RecipeManager(recipeList);
		
		//Should remove "Homemade Pizza"
		recipeManager.removeRecipe(1);
		
		String solution0 = "Egg Sandwich";
		String solution1 = "Bacon-Wrapped Tenderloin";
		
		assertEquals(recipeManager.getRecipe(0).getName(), solution0);
		assertEquals(recipeManager.getRecipe(1).getName(), solution1);
	}
	
	@Test
	public void getAllUniqueIngredientsFromRecipeManager() {
		
			//Tested here is:
				//addRecipe
				//getRecipe
				//removeRecipe
				//constructor
				//getAllUniqueIngredients
		
		
		//Recipe 1
		ArrayList<Ingredient> recipeIngredients1 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps1 = new ArrayList<String>();
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		
		recipeIngredients1.add(new Ingredient("Eggs", "egg", 2.0, 2.0));
		recipeIngredients1.add(new Ingredient("Cheese", "slice", 1.5, 2.0));
		recipeIngredients1.add(new Ingredient("Bread", "slice", 0.50, 2.0));
		recipeIngredients1.add(new Ingredient("Butter", "stick", 0.75, 1.0));
		recipeIngredients1.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		recipeSteps1.add("1. Toss eggs into pan heated to medium-high heat");
		recipeSteps1.add("2. Stir occasionally for no more than 4-5 minutes");
		recipeSteps1.add("3. Put bread in toaster");
		recipeSteps1.add("4. Seriously it's an egg sandwich its not that hard");
		
		Recipe recipe1 = new Recipe("Egg Sandwich", recipeIngredients1, "Stove", 10.0, "URL GOES HERE", recipeSteps1);
		
		//Recipe 2
		ArrayList<Ingredient> recipeIngredients2 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps2 = new ArrayList<String>();
		
		recipeIngredients2.add(new Ingredient("Pizza Sauce", "tablespoon", 2.0, 2.0));
		recipeIngredients2.add(new Ingredient("Pizza Crust", "slice", 1.50, 1.0));
		recipeIngredients2.add(new Ingredient("Shredded Cheese", "cup", 0.55, 1.25));
		recipeIngredients2.add(new Ingredient("Pepperoni", "piece", 2.50, 2.0));
		recipeIngredients2.add(new Ingredient("Butter", "stick", 0.75, 1.0));
		recipeSteps2.add("1. Spread sauce evenly across pizza");
		recipeSteps2.add("2. Sprinkle the shredded cheese across the pizza");
		recipeSteps2.add("3. Place pieces of pepperoni on the pizza");
		recipeSteps2.add("4. Place pizza in oven for about 5-6 minutes");
		recipeSteps2.add("5. Remove from oven, let sit and cool for a minute, slice and serve");
		
		Recipe recipe2 = new Recipe("Homemade Pizza", recipeIngredients2, "Oven", 15.0, "URL GOES HERE", recipeSteps2);
		
		//Recipe 3
		ArrayList<Ingredient> recipeIngredients3 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps3 = new ArrayList<String>();
		
		recipeIngredients3.add(new Ingredient("Tenderloin Steak", "ounce", 8.5, 6.4));
		recipeIngredients3.add(new Ingredient("Bacon", "strip", 1.25, 1.0));
		recipeIngredients3.add(new Ingredient("Salt", "teaspoon", 0.05, 1.25));
		recipeIngredients3.add(new Ingredient("Olive Oil", "teaspoon", 1.35, 1.0));
		recipeIngredients3.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		recipeSteps3.add("1. Season your steak with salt and pepper");
		recipeSteps3.add("2. Apply olive oil and rub oil and seasoning into the steak");
		recipeSteps3.add("3. Wrap a strip around the tenderloin");
		recipeSteps3.add("4. Heat a pan to medium-high heat, use either butter or olive oil to avoid sticking");
		recipeSteps3.add("5. Gently place tenderloin in center of pan. Sear 6 minutes each side to desired doneness");
		recipeSteps3.add("6. Once cooked to your preference, let it sit and cool for a minute, serve and enjoy");
		
		Recipe recipe3 = new Recipe("Bacon-Wrapped Tenderloin", recipeIngredients3, "Stove", 25.0, "URL GOES HERE", recipeSteps3);
		
		recipeList.add(recipe1);
		recipeList.add(recipe2);
		recipeList.add(recipe3);
		
		//Recipe List and Manager
		RecipeManager recipeManager = new RecipeManager(recipeList);
		
		//Filter unique ingredients
		ArrayList<Ingredient> uniqueIngredients = new ArrayList<Ingredient>(recipeManager.getAllUniqueIngredients());
		
		String ingredientString = "";

		for (Ingredient i : uniqueIngredients)
		{
			ingredientString += i.getName() + "\t";
		}
		//Probably want to display this just in case
		//System.out.println(ingredientString);
		String solution = ingredientString;
		
		assertEquals(ingredientString, solution);
	}
	
	@Test
	public void testIngredientEquality(){
		Ingredient ingredient1 = new Ingredient("Mustard", "cup", 20.0, 20.0);
		Ingredient ingredient2 = new Ingredient("Mustard", "cup", 20.0, 20.0);
		System.out.println("begin comparison");
		assertEquals(ingredient1, ingredient2);
	}
	
	@Test
	public void testFilteringandOrdering() {
		
		//Recipe 1
		ArrayList<Ingredient> recipeIngredients1 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps1 = new ArrayList<String>();
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		
		recipeIngredients1.add(new Ingredient("Eggs", "egg", 2.0, 2.0));
		recipeIngredients1.add(new Ingredient("Cheese", "slice", 1.5, 2.0));
		recipeIngredients1.add(new Ingredient("Bread", "slice", 0.50, 2.0));
		recipeIngredients1.add(new Ingredient("Butter", "stick", 0.75, 1.0));
		recipeIngredients1.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		recipeSteps1.add("1. Toss eggs into pan heated to medium-high heat");
		recipeSteps1.add("2. Stir occasionally for no more than 4-5 minutes");
		recipeSteps1.add("3. Put bread in toaster");
		recipeSteps1.add("4. Seriously it's an egg sandwich its not that hard");
		
		Recipe recipe1 = new Recipe("Egg Sandwich", recipeIngredients1, "Stove", 10.0, "URL GOES HERE", recipeSteps1);
		
		//Recipe 2
		ArrayList<Ingredient> recipeIngredients2 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps2 = new ArrayList<String>();
		
		recipeIngredients2.add(new Ingredient("Pizza Sauce", "tablespoon", 2.0, 2.0));
		recipeIngredients2.add(new Ingredient("Pizza Crust", "slice", 1.50, 1.0));
		recipeIngredients2.add(new Ingredient("Shredded Cheese", "cup", 0.55, 1.25));
		recipeIngredients2.add(new Ingredient("Pepperoni", "piece", 2.50, 2.0));
		recipeIngredients2.add(new Ingredient("Butter", "stick", 0.75, 1.0));
		recipeSteps2.add("1. Spread sauce evenly across pizza");
		recipeSteps2.add("2. Sprinkle the shredded cheese across the pizza");
		recipeSteps2.add("3. Place pieces of pepperoni on the pizza");
		recipeSteps2.add("4. Place pizza in oven for about 5-6 minutes");
		recipeSteps2.add("5. Remove from oven, let sit and cool for a minute, slice and serve");
		
		Recipe recipe2 = new Recipe("Homemade Pizza", recipeIngredients2, "Oven", 15.0, "URL GOES HERE", recipeSteps2);
		
		//Recipe 3
		ArrayList<Ingredient> recipeIngredients3 = new ArrayList<Ingredient>();
		ArrayList<String> recipeSteps3 = new ArrayList<String>();
		
		recipeIngredients3.add(new Ingredient("Tenderloin Steak", "ounce", 8.5, 6.4));
		recipeIngredients3.add(new Ingredient("Bacon", "strip", 1.25, 1.0));
		recipeIngredients3.add(new Ingredient("Salt", "teaspoon", 0.05, 1.25));
		recipeIngredients3.add(new Ingredient("Olive Oil", "teaspoon", 1.35, 1.0));
		recipeIngredients3.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		recipeSteps3.add("1. Season your steak with salt and pepper");
		recipeSteps3.add("2. Apply olive oil and rub oil and seasoning into the steak");
		recipeSteps3.add("3. Wrap a strip around the tenderloin");
		recipeSteps3.add("4. Heat a pan to medium-high heat, use either butter or olive oil to avoid sticking");
		recipeSteps3.add("5. Gently place tenderloin in center of pan. Sear 6 minutes each side to desired doneness");
		recipeSteps3.add("6. Once cooked to your preference, let it sit and cool for a minute, serve and enjoy");
		
		Recipe recipe3 = new Recipe("Bacon-Wrapped Tenderloin", recipeIngredients3, "Stove", 25.0, "URL GOES HERE", recipeSteps3);
		
		recipeList.add(recipe1);
		recipeList.add(recipe2);
		recipeList.add(recipe3);
		
		//Recipe Manager
		RecipeManager recipeManager = new RecipeManager(recipeList);
		
		ArrayList<Ingredient> filters = new ArrayList<Ingredient>();
		
		filters.add(new Ingredient("Pepper", "teaspoon", 0.12, 0.5));
		filters.add(new Ingredient("Bacon", "strip", 1.25, 1.0));
		
		ArrayList<Recipe> correct_ordering = new ArrayList<Recipe>();
		
		correct_ordering.add(recipe3);
		correct_ordering.add(recipe1);
		
		assertEquals(correct_ordering, recipeManager.filter(filters, 0, Double.MAX_VALUE, 0, Double.MAX_VALUE));
	}
}
*/