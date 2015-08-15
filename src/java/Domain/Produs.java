/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Bazis
 */
public class Produs {
    
    int Id;
    String name;
    String description;
    double price;

    public Produs() {
    }

    public Produs(int Id, String name, String description, double price) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

  
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Produs{" + "Id=" + Id + ", name=" + name + ", description=" + description + ", price=" + price + '}';
    }
    
    
}
