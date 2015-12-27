package com.example.owner1.pocketchef;

import java.util.Vector;

/**
 * Created by Owner1 on 11/16/2015.
 *
 */
public class OldRecipe {
    String name = "default";
    String ingredients = "Some ingredients.";
    String preparationTime = "69 min.";
    String appliances = "microwave";
    String instructions = "Step 1: \n Step 2: \n ...";
    String url = "https://metrouk2.files.wordpress.com/2014/02/ad128133577this-picture-tak.jpg";

    public OldRecipe() {
        name = "fsdffdds";
        preparationTime = "";
    }

    public OldRecipe(String n, String i, String p, String a, String in, String u) {
        name = n;
        ingredients = i;
        preparationTime = p;
        appliances = a;
        instructions = in;
        url = u;
    }

    public OldRecipe(OldRecipe r) {
        name = r.getName();
        ingredients = getIngredients();
        preparationTime = getPrepTime();
        appliances = getAppliances();
        instructions = getInstructions();
        url = getURL();
    }

    public void setName(String s) {
        name = s;
    }

    public void setIngredients(String s) {
        ingredients = s;
    }

    public void setPrepTime(String s) {
        preparationTime = s;
    }

    public void setAppliances(String s) {
        appliances = s;
    }

    public void setInstructions(String s) {
        instructions = s;
    }

    public void setURL(String u) { url = u; }


    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getPrepTime() {
        return preparationTime;
    }

    public String getAppliances() {
        return appliances;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getURL() { return url; }

    public static Vector<OldRecipe> getRecipes() {
        Vector<OldRecipe> recipes = new Vector<>();

        recipes.add(new OldRecipe("Steak", "3", "5 hours", "5", "5", "https://chicolockersausage.files.wordpress.com/2012/01/grilled-rare-steak.jpg"));
        recipes.add(new OldRecipe("Pizza", "1", "1 hour", "5", "5", "http://www.wgrr.com/wp-content/uploads/sites/572/2015/10/2015-05-19_pizza.jpg"));
        recipes.add(new OldRecipe("Spaghetti", "4", "5 hours", "5", "5", "http://img.sndimg.com/food/image/upload/w_555,h_416,c_fit,fl_progressive,q_95/v1/img/recipes/22/78/2/picrIZyXg.jpg"));
        recipes.add(new OldRecipe("Pancakes", "5", "5 hours", "5", "5", "http://blog.patriotgetaways.com/wp-content/uploads/2010/04/pancakes.jpg?w=300"));

        //"Scrambled Eggs", ".5", "5 hours", "5", "5", "https://i.ytimg.com/vi/R4vDqlKMbrk/hqdefault.jpg"
        OldRecipe eggs = new OldRecipe();
        eggs.setName("Scambled Eggs");
        eggs.setIngredients("3 Eggs");
        eggs.setPrepTime("1 Minute");
        eggs.setAppliances("Stove");
        eggs.setInstructions("1. Heat the stove.\n2. Crack the eggs into a frying pan.\n3. Scramble eggs with spatula/fork/whisk while heating.\n4. Profit.");
        eggs.setURL("https://i.ytimg.com/vi/R4vDqlKMbrk/hqdefault.jpg");
        recipes.add(eggs);

        recipes.add(new OldRecipe("PB & J", "2.5", "5 hours", "5", "5", "http://vignette1.wikia.nocookie.net/central/images/8/83/01b56716c0573873fa57b8d5e365ea3c.jpg/revision/latest?cb=20150906175045"));
        recipes.add(new OldRecipe("Cake", "things", "999 hours", "5", "5", "http://crossfit601.com/wp-content/uploads/2015/03/article-0-14FA374C000005DC-973_634x467.jpg"));

        return recipes;
    }

    @Override
    public String toString() {
        return name;
    }
}
