# Requirements

## Bezoeker
Een anonieme gebruiker van de website.

## Abbonnement
- Als gebruiker, wil ik een abonnement kunnen aanschaffen.
- Als (kind) gebruiker, wil ik een kinderabonnement kunnen aanschaffen.
- Als gebruiker, in bezit van een abonnement wil ik kunnen inloggen. Hierna ben ik een _lid_. 
- Als gebruiker, wil ik een volwassenabonnement kunnen aanschaffen.
- Als gebruiker, wil ik een selectief digitaal abonnement kunnen aanschaffen.

## Catalogus
- Als gebruiker, wil ik de catalogus kunnen doorzoeken, door eventueel te filteren op basis van:
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
- Als lid, wil ik een notificatie ontvangen wanneer een gereserveerd product beschikbaar is geworden om te lenen.


### Overig
- Als volwassen lid, wil ik de leenhistorie van mijn gekoppelde kinderabonnementen kunnen inzien.

## Editor
Een gebruiker die zorgt dat het systeem inhoudelijk klopt. 

- Als editor, wil ik een limiet (overschrijft algemeen leenlimiet) kunnen instellen voor een categorie van een fysiek product.
- Als editor, wil ik een limiet (overschrijft algemeen reserveerlimiet) kunnen instellen voor een categorie van een fysiek product.

- Als editor, wil ik een product kunnen uitlichten. Ze worden aangeprezen bij gebruikers.
- Als editor, wil ik een (concept) product kunnen toevoegen (met bijhorende info). Een concept product is nog niet zichtbaar in de catalogus.
- Als editor, wil ik een product zijn gegevens kunnen aanpassen.
- Als editor, wil ik een product kunnen publiceren.
- Als editor, wil ik een product veranderen naar een concept. Deze is dan niet zichtbaar in de catalogus.

## Beheerder
Een gebruiker die?

- Als beheerder, kan ik een lid (handmatig) toevoegen.
- Als beheerder, kan ik een lid (handmatig) verwijderen. De bijhorende gegevens worden volledig verwijderd.
- Als beheerder, kan ik een lid (handmatig) zijn gegevens aanpassen.
- Als beheerder, kan ik een lid blokkeren. Hierdoor heeft een lid geen toegang tot producten meer.


## Definities
### Kinderabonnement
Dit abonnement is exclusief beschikbaar om aan te schaffen voor bezoekers niet ouder dan 16 jaar.

Dit abonnement geeft alleen toegang tot de kinderproducten.

Dit abonnement kan niet bestaan zonder een gekoppeld volwassen lid (met abonnement).

### Selectief digitaal abonnement
Dit abonnement is goedkoper dan een _normaal_ volwassen abonnement. 

Dit abonnement geeft alleen toegang tot digitale producten.

## Zonder rol
- Er is een limiet ingesteld voor het aantal geleende (fysieke) producten per gebruiker voor elk abonnementstype.
- Er is een limiet ingesteld voor het aantal gereserveerde (fysieke) producten per gebruiker voor elk abonnementstype.
- Er loopt een boete op wanneer een lid een fysiek product niet heeft teruggebracht binnen het leentermijn. 
- Er is een maximale boete vastgesteld, wanneer dit bedrag is bereikt heeft het lid geen toegang meer tot producten.
- Er is sprake van een boete wanneer een lid 3 x in serie niet gebruik maakt van zijn reserveringen op producten.
- Toegang tot elektronische producten worden ingetrokken wanneer de leenduur verstreken is.
- Abonnementen moeten de mogelijkheid hebben voor een hoger leenlimiet.
- Abonnementen moeten de mogelijkheid hebben voor een hoger reserveerlimiet.