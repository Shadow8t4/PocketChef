package com.example.owner1.pocketchef;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

        import android.content.Context;
        import android.util.Xml;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
        import org.xmlpull.v1.XmlPullParserException;
        import org.xmlpull.v1.XmlSerializer;
import java.io.InputStream;
        import java.io.OutputStreamWriter;
        import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

//this is HEAVILY taken / adapted from Android Developers
public class RecipeBookXMLHandler {
    private static final String ns = null;
    private static String something = "";

    //parses a given InputStream
    public RecipeManager parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();

            return new RecipeManager(readRecipeBook(parser));
        }
        finally {
            in.close();
        }
    }

    //reads from a given XMLPullParser
    private ArrayList<Recipe> readRecipeBook(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Recipe> recipes = new ArrayList();
        String pname = "";

        parser.require(XmlPullParser.START_TAG, ns, "recipebook");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            pname = parser.getName();
            if (pname.equals("recipe")){
                recipes.add(readRecipe(parser));
            }

            else {
                skip(parser);
            }
        }

        return recipes;
    }

    private Recipe readRecipe(XmlPullParser parser) throws XmlPullParserException, IOException {
        Recipe r;
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ArrayList<String> text = new ArrayList<String>();
        String pname = "";
        String name = "";
        String appliance = "";
        double time = 0.0;
        double cost = 0.0;
        String image = "";

        parser.require(XmlPullParser.START_TAG, ns, "recipe");
        name = parser.getAttributeValue(ns, "recipename");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            pname = parser.getName();
            if (pname.equals("ingredient")){
                ingredients.add(readIngredient(parser));
            }

            else if (pname.equals("appliance")){
                appliance = readAppliance(parser);
            }

            else if (pname.equals("time")){
                time = readTime(parser);
            }

            else if (pname.equals("cost")){
                cost = readCost(parser);
            }

            else if (pname.equals("image")){
                image = readImage(parser);
            }

            else if (pname.equals("text")){
                text = readSteps(parser);
            }

            else {
                skip(parser);
            }
        }

        r = new Recipe(name, ingredients, appliance, time, image, text);
        return r;
    }

    private Ingredient readIngredient(XmlPullParser parser) throws XmlPullParserException, IOException {
        Ingredient ingredient;
        String pname = "";
        String name = "";
        String unit = "";
        double quantity = 0.0;
        double cost = 0.0;

        parser.require(XmlPullParser.START_TAG, ns, "ingredient");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            pname = parser.getName();
            if (pname.equals("name")){
                parser.require(XmlPullParser.START_TAG, ns, "name");
                name = readText(parser);
                parser.require(XmlPullParser.END_TAG, ns, "name");
            }

            else if (pname.equals("unit")){
                parser.require(XmlPullParser.START_TAG, ns, "unit");
                unit = readText(parser);
                parser.require(XmlPullParser.END_TAG, ns, "unit");
            }

            else if (pname.equals("costperunit")){
                parser.require(XmlPullParser.START_TAG, ns, "costperunit");
                cost = Double.parseDouble(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "costperunit");
            }

            else if (pname.equals("quantity")){
                parser.require(XmlPullParser.START_TAG, ns, "quantity");
                quantity = Double.parseDouble(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "quantity");
            }

            else {
                skip(parser);
            }
        }

        parser.require(XmlPullParser.END_TAG, ns, "ingredient");
        ingredient = new Ingredient(name, unit, cost, quantity);
        return ingredient;
    }

    private String readAppliance(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "appliance");
        String appliance = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "appliance");

        return appliance;
    }

    private double readTime(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "time");
        double time = Double.parseDouble(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "time");

        return time;
    }

    private double readCost(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "cost");
        double cost = Double.parseDouble(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "cost");

        return cost;
    }

    private String readImage(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "image");
        String image = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "image");

        return image;
    }

    private ArrayList<String> readSteps(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<String> steps = new ArrayList<String>();
        String name = "";

        parser.require(XmlPullParser.START_TAG, ns, "text");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            name = parser.getName();

            if (name.equals("step")){
                steps.add(readStep(parser));
            }
        }

        parser.require(XmlPullParser.END_TAG, ns, "text");

        return steps;
    }

    private String readStep(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "step");
        String step = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "step");

        return step;
    }

    //taken directly from Android Developers
    private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";

        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }

        return result;
    }

    //taken directly from Android Developers
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG){
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public RecipeManager readXML(String fileName, Context ctx){
        InputStream inputStream;

        try {
            //inputStream = ctx.openFileInput("config.txt");
            inputStream = ctx.openFileInput(fileName);
            return parse(inputStream);
        }

        catch(Exception e){
            System.out.println(e);
            e.getStackTrace();
            return new RecipeManager();
        }
    }

    public RecipeManager readXML(int id, Context ctx){
        InputStream inputStream;

        try {
            //inputStream = ctx.openFileInput("config.txt");
            inputStream = ctx.getResources().openRawResource(id);

            return parse(inputStream);
        }

        catch(Exception e){
            System.out.println(e);
            e.getStackTrace();
            return new RecipeManager();
        }
    }

    //Write to an XML file
    public boolean writeXML(RecipeManager recipeManager, String fileName, Context ctx)
    {
        ArrayList<Recipe> recipes = recipeManager.getRecipes();
        XmlSerializer serializer = Xml.newSerializer();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(fileName, Context.MODE_PRIVATE));
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));

            //Generate an XML file
            serializer.setOutput(outputStreamWriter);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "recipebook");

            //Iterate through recipes
            for(Recipe rcp : recipes){
                serializer.startTag("", "recipe");
                serializer.attribute("", "recipename", rcp.getName());

                //Iterate through ingredients in each recipe
                for(Ingredient ing : rcp.getIngredients()){
                    //Add name of ingredient
                    serializer.startTag("", "ingredient");
                    serializer.startTag("", "name");
                    serializer.text(ing.getName());
                    serializer.endTag("", "name");

                    //Add unit of ingredient
                    serializer.startTag("", "unit");
                    serializer.text(ing.getUnit());
                    serializer.endTag("", "unit");

                    //Add cost per unit of ingredient
                    serializer.startTag("", "costperunit");
                    serializer.text(String.valueOf(ing.getCost()));
                    serializer.endTag("", "costperunit");

                    //Add quantity of ingredient
                    serializer.startTag("", "quantity");
                    serializer.text(String.valueOf(ing.getQuantity()));
                    serializer.endTag("", "quantity");
                    serializer.endTag("", "ingredient");
                }

                serializer.startTag("", "appliance");
                serializer.text(rcp.getAppliance());
                serializer.endTag("", "appliance");

                serializer.startTag("", "time");
                serializer.text(String.valueOf(rcp.getTime()));
                serializer.endTag("", "time");

                serializer.startTag("", "cost");
                serializer.text(String.valueOf(rcp.getCost()));
                serializer.endTag("", "cost");

                serializer.startTag("", "image");
                serializer.text(rcp.getURL());
                serializer.endTag("", "image");

                serializer.startTag("", "text");
                for(String txt : rcp.getText()){

                    serializer.startTag("", "step");
                    serializer.text(txt);
                    serializer.endTag("", "step");
                }
                serializer.endTag("", "text");

                serializer.endTag("", "recipe");
            }

            serializer.endTag("", "recipebook");
            serializer.endDocument();

            /*
            if (validateRecipeBookXMLSchema(fileName, ctx))
                return true;
            else return false;
            */

            return true;
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
    public static boolean validateRecipeBookXMLSchema(String xmlPath, Context ctx){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			InputStream is = ctx.getResources().openRawResource(R.raw.recipebookxsd);
			Source schemaFile = new StreamSource(is);
			
			Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        }
        catch (IOException e){
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        catch(SAXException e1){
            System.out.println("SAX Exception: "+e1.getMessage());
            return false;
        }
        return true;
    }
    //
}