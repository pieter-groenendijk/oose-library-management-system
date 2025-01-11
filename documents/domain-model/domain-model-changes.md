# Changes for domain model v2

## Leenhistorie
- **Correcte syntax toegepast**
- 1 leenhistorie heeft 0 ... 1 abonnement -> **1 leenhistorie heeft 1 ... 1 abonnement**. Het is nutteloos om een leenhistorie
aan te maken dat niet gekoppeld is aan een abonnement.
- 1 leenhistorie heeft 1 ... \*uitlening -> **1 leenhistorie heeft 0 ...\***. In de praktijk is het leenhistorie concept
puur wat records in de database. Als men dus nog niks heeft geleend is deze gewoon leeg.
- **Associaties verwoordingen verduidelijkt**

## Catalogus
- **Correcte syntax toegepast**
- **Composition toegepast**. De catalogus bestaat uit producten.
- **Associaties verwoordingen verduidelijkt**

## Digitaal product en fysiek product
- **Redundant concept _digitaal product_ verwijdert, herplaats met twee specialisatie van de generalisatie _product_**.
We kunnen nu gemakkelijker associaties koppelen.
- **_Exemplaar_ concept is nu gekoppeld aan _fysiek product_**. Je kan niet een digitaal product lenen.

### Mijn bibliotheek
**Toegevoegd**

Net zoals bij de usecase representeerd dit concept de registraties van digitale producten in gebruik onder het abonnement.

### Digitaal Product Gebruik
**Toegevoegd**

## Uitlening
- **1 uitlening heeft 1...\* -> 1 uitlening heeft 1..1**. 
- **associatie naar 'van'**

## Changes naar Domain Model v4

- Een bezoeker krijgt géén plaats in het domein model. Dit is een concept dat buiten het systeem valt.
- Een Baliemedewerker is een gebruiker die specifieke rechten heeft. Dit is een specialisatie van de gebruiker.
- De Beheerder en Editor zijn opgenomen in het Domein Model omdat zij de kern functionaliteit van de Boetes/Limieten en de Catalogus beheren en dus kunnen veranderen.
- Leenhistorie komt voor uit gebruik van een digitaal product of uit een exemplaar uitlening.
- Leenhistorie en aggregatie relatie met ProductCopy en DigitaalProductGebruik.
- Catalogus heeft een aggregatie relatie met Product.
