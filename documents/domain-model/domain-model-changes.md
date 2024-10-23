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
**Toegevoegt**

Net zoals bij de usecase representeerd dit concept de registraties van digitale producten in gebruik onder het abonnement.

### Digitaal Product Gebruik
**Toegevoegd**


