# Design Patterns voor Producten
Producten zijn een belangrijk onderdeel van een applicatie. Producten bevatten o.a  een titel, beschrijving, genre, leeftijdsclassificatie, auteur en een aantal exeplaren.
In dit document worden een aantal design patterns besproken die gebruikt kunnen worden bij het ontwerpen van producten.

Producten in dit domein: Boeken, Tijdsschriften, Audio boeken en E-books

### Optie 1. Template method <br>
De template method is een design pattern dat gebruikt kan worden om een algoritme te definiëren in een superklasse en de implementatie van een of meerdere stappen van het algoritme over te laten aan subklassen. Dit pattern kan gebruikt worden om de implementatie van een product te definiëren in een superklasse en de implementatie van een of meerdere stappen van het product over te laten aan subklassen.

#### Voorbeeld
1. Maak een abstracte klasse Product met de volgende attributen: titel, beschrijving, genre, leeftijdsclassificatie, auteur en aantal exemplaren.<br> Deze klasse bevat ook de Template methode <em> manageProduct </em><br>
2. Maak concrete subklassen van Product. Bijvoorbeeld: Boek, Tijdschrift, AudioBoek en E-Book. <br>
3. Implementeer de abstracte Template methode <em> manageProduct </em> in de subklassen. Deze methode bevat de stappen die specifiek zijn voor dat type product.<br>
   
Template Method (manageProduct): Deze methode definieert de volgorde van de stappen voor het beheren van een product: toevoegen aan de catalogus, controleren of het uitgeleend kan worden, en eventueel uitlenen aan een lid.<br>
Java
```java 
public abstract class Product {
    private String title;
    private String description;
    private String genre;
    private String ageRating;
    private String author;
    private int numberOfCopies;

    // Constructor
    public Product(String title, String description, String genre, String ageRating, String author, int numberOfCopies) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.ageRating = ageRating;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
    }

    // Template method
    public final void manageProduct() {
        addToCatalog();
    }

    // Getters en setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }
}
public class Book extends Product {
    public Book(String title, String description, String genre, String ageRating, String author, int numberOfCopies) {
        super(title, description, genre, ageRating, author, numberOfCopies);
    }
}

```

#### Voordelen
1. Eenvoudig uit te breiden als je een nieuw producttype wilt toevoegen
2. Loose coupling tussen de aanroepende code en de productklassen
3. Uniforme creatie

### Optie 2. Factory method <br>
De factory method is een design pattern dat gebruikt kan worden om een object te maken zonder de exacte klasse van het object te specificeren. Dit pattern kan gebruikt worden om een product te maken zonder de exacte klasse van het product te specificeren.

#### Voorbeeld
1. Maak een abstracte klasse Product met de volgende attributen: titel, beschrijving, genre, leeftijdsclassificatie, auteur en aantal exemplaren.<br>
2. Maak productklassen die de abstracte klasse Product implementeren: Boek, Tijdschrift, AudioBoek en E-Book. Deze klassen moeten de abstracte methoden implementeren en kunnen specifieke eigenschappen bevatten die uniek zijn voor dat type product.<br>
3. Maak een ProductFactory klasse die de verantwoordelijkheid heeft om een product te maken op basis van een bepaald type.
4. Gebruik ProductFactory om verschillende producttypen te maken zonder dat de aanroepende code hoeft te weten welke specifieke klasse wordt aangemaakt.

Java
```java
public abstract class Product {
    private String titel;
    private String beschrijving;
    private String genre;
    private String leeftijdsclassificatie;
    private String auteur;
    private int aantalExemplaren;

    public Product(String titel, String beschrijving, String genre, String leeftijdsclassificatie, String auteur, int aantalExemplaren) {
        this.titel = titel;
        this.beschrijving = beschrijving;
        this.genre = genre;
        this.leeftijdsclassificatie = leeftijdsclassificatie;
        this.auteur = auteur;
        this.aantalExemplaren = aantalExemplaren;
    }

    public abstract void display();
}

public class Book extends Product {
public Book(String title, String description, String genre, String ageRating, String author, int numberOfCopies) {
super(title, description, genre, ageRating, author, numberOfCopies);
}

public class ProductFactory {
    public static Product createProduct(String type, String title, String description, String genre, String ageRating, String author, int numberOfCopies) {
        switch (type.toLowerCase()) {
            case "book":
                return new Book(title, description, genre, ageRating, author, numberOfCopies);
        }
    }
}
};
``` 




