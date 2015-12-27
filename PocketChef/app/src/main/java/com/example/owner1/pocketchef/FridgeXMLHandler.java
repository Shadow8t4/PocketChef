package com.example.owner1.pocketchef;

import android.content.Context;
import android.util.Xml;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.IOException;
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

public class FridgeXMLHandler {
    private static final String ns = null;
    private static String something = "";

    //parses a given InputStream
    public Fridge parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();

            return readFridge(parser);
        }
        finally {
            in.close();
        }
    }

    //reads from a given XMLPullParser
    private Fridge readFridge(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Ingredient> ingredients = new ArrayList();
        String pname = "";

        parser.require(XmlPullParser.START_TAG, ns, "fridge");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            pname = parser.getName();
            if (pname.equals("ingredient")){
                ingredients.add(readIngredient(parser));
            }

            else {
                skip(parser);
            }
        }

        return new Fridge(ingredients);
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

    public Fridge readXML(String fileName, Context ctx){
        InputStream inputStream;

        try {
            inputStream = ctx.openFileInput(fileName);
            return parse(inputStream);
        }

        catch(Exception e){
            System.out.println(e);
            e.getStackTrace();
            return new Fridge();
        }
    }

    public Fridge readXML(int id, Context ctx){
        InputStream inputStream;

        try {
            inputStream = ctx.getResources().openRawResource(id);
            return parse(inputStream);
        }

        catch(Exception e){
            System.out.println(e);
            e.getStackTrace();
            return new Fridge();
        }
    }

    public boolean writeXML(Fridge fridge, String fileName, Context ctx)
    {
        ArrayList<Ingredient> ingredients = fridge.getIngredients();
        XmlSerializer serializer = Xml.newSerializer();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(fileName, Context.MODE_PRIVATE));

            serializer.setOutput(outputStreamWriter);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "fridge");

			for(Ingredient ing : ingredients){
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

            serializer.endTag("", "fridge");
            serializer.endDocument();
            /*
            if (validateFridgeXMLSchema(fileName))
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
    public static boolean validateFridgeXMLSchema(String xmlPath, Context context){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
			InputStream is = context.getResources().openRawResource(R.raw.fridgexsd);
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
