# Verbeteringen mogelijk op het ontwerp

## Producten <br>
#### Geimplementeerd: Template Pattern <br>
Momenteel worden producten gemaakt met behulp van het Template Design Pattern. <br>
Er is een superclass ProductTemplate, die wordt uitgebreid door twee subclasses: PhysicalProductTemplate en DigitalProductTemplate. <br>
De implementaties van de producten zelf, zoals PhysicalProduct en DigitalProduct, volgen deze templates. <br>

#### Verbetering: Strategy Pattern <br>
Een alternatief voor dit ontwerp zou het gebruik van het Strategy Pattern kunnen zijn, waarbij de verschillende producttypes worden behandeld door verschillende strategieën voor de implementatie, in plaats van via de inheritance van templates. <br>
Dit zou meer flexibiliteit bieden bij het wisselen van strategieën zonder de noodzaak van subclassing. <br>
Product (model): De hoofdklasse die gebruik maakt van een strategie om het product te creëren of te behandelen. Dit zou een verwijzing bevatten naar de ProductStrategy. <br>

ProductStrategy (interface): Een interface die de verschillende strategieën definieert voor het behandelen van producten. Elke strategie zou de specifieke implementatie bevatten voor de creatie of afhandeling van een product. <br>

PhysicalProductStrategy (concrete strategie): Een implementatie van de ProductStrategy interface, die de logica voor fysieke producten bevat. <br>

DigitalProductStrategy (concrete strategie): Een implementatie van de ProductStrategy interface, die de logica voor digitale producten bevat. <br>


## Lening en Reservering <br>
#### Geimplementeerd: Géén design pattern <br>
Leningen en Reserveringen zijn allebij een eigen superclass. Er wordt gebruik gemaakt van inheritance om de verschillende types van leningen en reserveringen te behandelen. <br>

#### Verbetering: Strategy Pattern <br>
Een alternatief voor het huidige ontwerp zou het gebruik van het Strategy Pattern kunnen zijn, waarbij de verschillende lening- en reserveringstypes worden behandeld door verschillende strategieën voor de implementatie, in plaats van via de inheritance van factories. <br>
Dit zou meer flexibiliteit bieden bij het wisselen van strategieën zonder de noodzaak van subclassing. <br> 

TransactieStrategy (context): De hoofdklassen die de gezamenlijke eigenschappen en logica voor leningen en reserveringen bevat. <br>
LeningStrategy of ReserveringStrategy om de transactie te creëren of te behandelen. Dit zou een verwijzing bevatten naar de TransactieStrategy. <br>
Lening (concrete strategie): Een implementatie van de Transactie interface, die de logica voor leningen bevat. <br>
Reservering (concrete strategie): Een implementatie van de Transactie interface, die de logica voor reserveringen bevat. <br>