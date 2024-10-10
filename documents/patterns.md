## Command Pattern
The command pattern is a behavioral design pattern in which an object is used to encapsulate all information needed to perform an action or trigger an event at a later time. 
This information includes the method name, the object that owns the method and values for the method parameters.

#### Waarom? 
Het Command Pattern wordt gebruikt om verzoeken of acties als objecten te behandelen, zodat deze kunnen worden aangeroepen, opgeslagen, of ongedaan gemaakt.

#### Toepassing:
Het lenen van een boek of tijdschrift is een actie die met het Command Pattern kan worden afgehandeld. 
Het kan bijvoorbeeld handig zijn om de uitleenactie later ongedaan te maken als er een fout optreedt, of om acties in een queue te plaatsen voor latere verwerking.



## Observer Pattern
The observer pattern is a behavioral design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them of state changes, usually by calling one of their methods.

#### Waarom?
Het Observer Pattern laat objecten weten wanneer een bepaalde gebeurtenis plaatsvindt. Dit is nuttig voor event-gebaseerde systemen.

#### Toepassing:
Wanneer een lid bijvoorbeeld een digitaal medium leent, kunnen andere delen van de applicatie, zoals een notificatieservice (bijvoorbeeld voor e-mailbevestigingen of meldingen), worden geïnformeerd dat de uitleenactie is voltooid. 
Dit voorkomt dat de applicatie direct moet weten wat er moet gebeuren na het lenen.



## Template Method Pattern
The template method pattern is a behavioral design pattern that defines the program skeleton of an algorithm in an operation, deferring some steps to subclasses. 
It lets one redefine certain steps of an algorithm without changing the algorithm's structure.

#### Waarom?
Het Template Method Pattern wordt gebruikt om een algoritme te definiëren in een operatie, waarbij sommige stappen worden uitgesteld naar subklassen.

#### Toepassing:
Als er verschillende soorten leenprocessen zijn (bijvoorbeeld fysieke boeken, digitale tijdschriften, of audioboeken), kan een template-methode de algemene logica van het uitleenproces definiëren, terwijl specifieke stappen voor elke mediatype kunnen worden ingevuld door subklassen.


## Facade Pattern
The facade pattern is a structural design pattern that provides a simplified interface to a larger body of code, such as a class library.

#### Waarom?
Het Facade Pattern wordt gebruikt om een eenvoudige interface te bieden voor een complex systeem of een reeks klassen.

#### Toepassing:
Het kan handig zijn om een Facade te creëren die de communicatie tussen verschillende subsysteemmodules (zoals authenticatie, bibliotheekcatalogus, en betaalsysteem) eenvoudig en overzichtelijk maakt. 
Hierdoor hoeven gebruikers niet rechtstreeks met de complexiteit van elk subsysteem om te gaan.