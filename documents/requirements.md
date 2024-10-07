# Requirements

## Actoren
| Gebruikerstype | Beschrijving                                                                  |
|:---------------|:------------------------------------------------------------------------------|
| Bezoeker       | Een bezoeker van de website zonder abonnement.                                |
| Lid            | Een klant van de bibliotheek. Komt in de vorm van verschillende abonnementen. |
| Editor         | Content beheerder. Zorgt dat het domein blijft kloppen.                       |
| Beheerder      | **Regelt personeel zaken? Onduidelijk**                                       | 

## Requirements
### Abonnement

| Actor                                         |                                                                | Prioriteit | Code   |
|:----------------------------------------------|:---------------------------------------------------------------|------------|--------|
| Als **bezoeker** in bezit van een abonnement, | wil ik kunnen inloggen. Hierna val ik in de rol _lid_.         | M          | FR-002 |
| Als **bezoeker**,                             | wil ik een _volwassen abonnement_ kunnen aanschaffen.          | M          | FR-003 |
| Als **bezoeker**,                             | wil ik een _selectief digitaal abonnement_ kunnen aanschaffen. | S          | FR-004 |
| Als **bezoeker** met een kind,                | wil ik een _kinder abonnement_ kunnen aanschaffen.             | C          | FR-001 |

### Catalogus

| Actor                 |                                                                                                                                      | Prioriteit | Code   |
|:----------------------|:-------------------------------------------------------------------------------------------------------------------------------------|------------|--------|
| Als **bezoeker/lid**, | wil ik de catalogus kunnen doorzoeken, door eventueel te filteren op basis van: titel, auteur/maker, beschikbaarheid en producttype. | M          | FR-005 |
| Als **bezoeker/lid**, | wil ik informatie kunnen inzien van een specifiek product.                                                                           | M          | FR-006 |
| Als **editor**,       | wil ik een product kunnen uitlichten.                                                                                                | S          | FR-007 |
| Als **editor**,       | wil ik een (concept) product kunnen toevoegen. (Nog niet zichtbaar in catalogus)                                                     | S          | FR-008 |
| Als **editor**,       | wil ik een product zijn gegevens kunnen aanpassen.                                                                                   | S          | FR-009 |
| Als **editor**,       | wil ik een product markeren als concept. (Niet meer zichtbaar in catalogus)                                                          | S          | FR-010 |
| Als **editor**,       | wil ik een product markeren als gepubliceerd. (Zichtbaar in catalogus)                                                               | S          | FR-011 |
| Als **editor**,       | wil ik een product kunnen verwijderen.                                                                                               | S          | FR-012 |
| Als **bezoeker/lid**, | wil ik kunnen inzien waar een fysiek product staat binnen de bibliotheek                                                             | C          | FR-045 |

### Accountbeheer

| Actor                                           |                                                                                    | Prioriteit | Code   |
|:------------------------------------------------|:-----------------------------------------------------------------------------------|------------|--------|
| Als **lid**,                                    | wil ik kunnen uitloggen. Hierna val ik in de rol _bezoeker_.                       | M          | FR-013 |
| Als **lid**,                                    | wil ik mijn leenhistorie kunnen inzien.                                            | S          | FR-014 |
| Als **beheerder**,                              | wil ik een lid kunnen toevoegen.                                                   | S          | FR-020 |
| Als **beheerder**,                              | wil ik een lid zijn gegevens kunnen aanpassen.                                     | S          | FR-021 |
| Als **beheerder**,                              | wil ik een lid kunnen verwijderen.                                                 | S          | FR-022 |
| Als **beheerder**,                              | wil ik een lid zijn account kunnen blokkeren. Het lid kan niet meer bij producten. | S          | FR-023 |
| Als **lid** met een gekoppeld kinderabonnement, | wil ik de leenhistorie van mijn kind kunnen inzien.                                | C          | FR-019 |

### Lenen

| Actor        |                                                                        | Prioriteit | Code   |
|:-------------|:-----------------------------------------------------------------------|------------|--------|
| Als **lid**, | wil ik een fysiek boek kunnen lenen.                                   | M          | FR-024 |
| Als **lid**, | wil ik een digitaal boek (ebook) kunnen lenen.                         | M          | FR-025 |
| Als **lid**, | wil ik een fysiek tijdschrift kunnen lenen.                            | M          | FR-026 |
| Als **lid**, | wil ik een digitaal tijdschrift kunnen lenen.                          | M          | FR-027 |
| Als **lid**, | wil ik _on demand_ een film kunnen bekijken.                           | S          | FR-028 |
| Als **lid**, | wil ik _on demand_ een audioboek kunnen beluisteren.                   | S          | FR-029 |
| Als **lid**, | wil ik het leentermijn van een geleend fysiek product kunnen verlengen | S          | FR-044 |

### Reserveren

| Actor        |                                                                                                                                                     | Prioriteit | Code   |
|:-------------|:----------------------------------------------------------------------------------------------------------------------------------------------------|------------|--------|
| Als **lid**, | wil ik een momenteel uitgeleend fysiek product kunnen reserveren indien deze nog niet door een ander is gereserveerd.                               | M          | FR-030 |


### Boetes

| Actor        |                                                                                                          | Prioriteit | Code   |
|:-------------|:---------------------------------------------------------------------------------------------------------|------------|--------|
| Als **lid**, | ben ik een opbouwende boete schuldig wanneer ik een geleend product niet binnen het leentermijn inlever. | M          | FR-031 |
| Als **lid**, | verlies ik toegang van het gehele productaanbod wanneer mijn schuld een vastgesteld bedrag bereikt.      | M          | FR-033 |
| Als **lid**, | wil ik mijn schuld kunnen betalen                                                                        | M          | FR-034 |
| Als **lid**, | ben ik een boete schuldig wanneer ik 3x in serie niet gebruik maak van een reservering.                  | S          | FR-032 |

### Limieten
| Actor           |                                                                                                                  | Prioriteit | Code   |
|:----------------|:-----------------------------------------------------------------------------------------------------------------|------------|--------|
| Als **lid**,    | geldt er voor mij een limiet (wat afhankelijk is van abonnement) voor geleende fysieke producten.                | M          | FR-015 |
| Als **lid**,    | geldt er voor mij een limiet (wat afhankelijk is van abonnement) voor gereserveerde fysieke producten.           | M          | FR-017 |
| Als **lid**,    | geldt er voor mij een limiet voor geleende fysieke producten uit een categorie (bijv. Harry Potter).[^1]         | S          | FR-016 |
| Als **lid**,    | geldt er voor mij een limiet voor gereserveerde fysieke producten vanuit een categorie (bijv. Harry Potter).[^2] | S          | FR-018 |
| Als **editor**, | wil ik het geleende fysieke producten limiet kunnen aanpassen.                                                   | S          | FR-035 |
| Als **editor**, | wil ik het geleende fysieke producten uit een categorie limiet kunnen aanpassen.                                 | S          | FR-036 |
| Als **editor**, | wil ik het gereserveerde fysieke producten limiet kunnen aanpassen.                                              | S          | FR-037 |
| Als **editor**, | wil ik het gereserveerde fysieke producten uit een categorie limiet kunnen aanpassen.                            | S          | FR-038 |

[^1]: Dit limiet overschrijft FR-015.
[^2]: Dit limiet overschrijft FR-017.

### Notificaties

| Actor        |                                                                                                                                                     | Prioriteit | Code   |
|:-------------|:----------------------------------------------------------------------------------------------------------------------------------------------------|------------|--------|
| Als **lid**, | wil ik een notificatie ontvangen wanneer het leentermijn van een geleend product verlopen is.                                                       | M          | FR-040 |
| Als **lid**, | wil ik een notificatie ontvangen wanneer mijn totale boete (schuld) opbouwt tot bepaalde bedragen.                                                  | S          | FR-042 |
| Als **lid**, | wil ik een notificatie ontvangen wanneer ik niet gebruik heb gemaakt van een reservering. Ik wordt geïnformeerd van een mogelijk toekomstige boete. | S          | FR-044 |
| Als **lid**, | wil ik een notificatie ontvangen wanneer het leentermijn van een geleend product bijna verloopt.                                                    | C          | FR-039 |
| Als **lid**, | wil ik wekelijks een notificatie ontvangen wanneer ik een _te laat_ boete schuldig ben van een geleend product.                                     | C          | FR-041 |
| Als **lid**, | wil ik een notificatie ontvangen wanneer een gereserveerd product beschikbaar is geworden om te lenen                                               | C          | FR-043 |

## Definities
### Kinderabonnement
Dit abonnement is exclusief beschikbaar om aan te schaffen voor bezoekers niet ouder dan 16 jaar.

Dit abonnement geeft alleen toegang tot de kinderproducten.

Dit abonnement kan niet bestaan zonder een gekoppeld volwassen lid (met abonnement).

### Selectief digitaal abonnement
Dit abonnement is goedkoper dan een _normaal_ volwassen abonnement. 

Dit abonnement geeft alleen toegang tot digitale producten.

## Vragen
- Is het niet wellicht makkelijker en duidelijker om services in te trekken wanneer de totale schuld een bepaald bedrag raakt i.p.v. specifiek voor het boetebedrag van lenen.
- Er moet altijd een volwassene gekoppeld zijn aan een kinderabonnement. Moet deze puur een account hebben of ook een volwassen abonnement?
- Momenteel is verlengen niet gelimiteerd? Wellicht willen we deze toevoegen?
