# Requirements

## Bezoeker
Een anonieme gebruiker van de website.

## Abbonnement
- Als gebruiker, wil ik een abonnement kunnen aanschaffen.
- Als gebruiker, in bezit van een abonnement wil ik kunnen inloggen. Hierna ben ik een _lid_. 

## Catalogus
- wil ik de catalogus kunnen doorzoeken, door eventueel te filteren op basis van:
    - Titel
    - Auteur/maker
    - Beschikbaarheid:
        - Beschikbaar
        - Niet beschikbaar
    - Producttype:
        - boek
        - tijdschrift
        - film
        - audiobook


## Lid
Een geauthenticeerde gebruiker met een abonnement.

### Accountbeheer
- Als lid, wil ik kunnen uitloggen. Hierna ben ik een bezoeker.

### Lenen
- Als lid, wil ik een fysiek boek kunnen lenen. **<-- Out of scope?**
- Als lid, wil ik een digitaal boek (ebook) kunnen lenen. 
- Als lid, wil ik een fysiek tijdschrift kunnen lenen. **<-- Out of scope?**
- Als lid, wil ik een digitaal tijdschrift kunnen lenen.
- Als lid, wil ik _on demand_ een film kunnen bekijken.
- Als lid, wil ik _on demand_ een audiobook kunnen beluisteren.

### Reserveren
- Als lid, wil ik een momenteel uitgeleend fysiek product kunnen reserveren indien deze nog niet door een ander is gereserveerd.
- Als lid, wil ik een openstaande boete kunnen betalen.

### Overig
- Als volwassen lid, wil ik de leenhistorie van mijn gekoppelde kinderabonnementen kunnen inzien.

## Editor
Een gebruiker die zorgt dat het systeem inhoudelijk klopt. 

- Als editor, wil ik een limiet (overschrijft algemeen leenlimiet) kunnen instellen voor een categorie van een fysiek product.
- Als editor, wil ik een limiet (overschrijft algemeen reserveerlimiet) kunnen installen voor een categorie van een fysiek product.

## Beheerder
Een gebruiker die?

## Zonder rol
- Er is een limiet ingesteld voor het aantal geleende (fysieke) producten per gebruiker voor elk abonnementstype.
- Er is een limiet ingesteld voor het aantal gereserveerde (fysieke) producten per gebruiker voor elk abonnementstype.
- Er is een een kinderabonnement aanwezig.
    - **Misschien gratis?**
    - Beperkt tot kinderproducten.
    - Gekoppeld aan volwassene.
- Er loopt een boete op wanneer een lid een fysiek product niet heeft teruggebreacht binnen het leentermijn. 
- Er is sprake van een boete wanneer een lid 3 x in serie niet gebruik maakt van zijn reserveringen op producten.

