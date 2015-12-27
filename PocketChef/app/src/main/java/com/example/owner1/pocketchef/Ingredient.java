package com.example.owner1.pocketchef;

public class Ingredient {
	private String name;
	private String unit;
	private double cost;
	private double quantity;

    Ingredient(){
        name = "";
        unit = "";
        cost = 0.0;
        quantity = 0.0;
    }

    Ingredient(String n, String u, double c, double q){
        name = n;
        unit = u;
        cost = c;
        quantity = q;
    }

    String getName(){ return name; }
    void setName(String n){ name = n; }
    String getUnit(){ return unit; }
    void setUnit(String u){ unit = u; }
    double getTotalCost(){ return cost * quantity; }
    double getCost(){ return cost; }
    void setCost(double c){ cost = c; }
    double getQuantity(){ return quantity; }
    void setQuantity(double q){ quantity = q; }

    public String toString(){
        return quantity + " " + unit + " " + name + " ($" + cost + "/" + unit + ")";
    }

    //does NOT take amounts into consideration!
    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }

        if (!(obj instanceof Ingredient)){
            return false;
        }

        Ingredient i = (Ingredient)obj;

        if ((i.getName().equals(name)) && (i.getUnit().equals(unit)) && (i.getCost() == cost) && (i.getQuantity() == quantity)){
            return true;
        }

        else return false;
    }

    public int hashCode(){
        int hc = 0;

        for (int i = 0; i < name.length(); ++i){
            hc += (int)(name.charAt(i));
        }

        for (int i = 0; i < unit.length(); ++i){
            hc += (int)(unit.charAt(i));
        }

        hc += cost;
        hc += quantity;

        return hc;
    }
}