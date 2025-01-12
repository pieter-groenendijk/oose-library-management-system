# Teamproces
Onze team bestaat uit 3 personen:
- Luc Derksen
- Marianne Peterson
- Pieter Groenendijk

De analysefase kwam als eerste. De designfase en implementatiefase worden meer iteratief per module
aangepakt. Deze keuze is gemaakt om de stof zo behapbaar te houden. 


## Versiebeheer
Onze project staat onder versiebeheer; _git_.

- De aanpassingen van een (normale) _commit_ zou verwoord moeten kunnen worden met 1 zin. 
De _commit message_.
- De _main_ branch is beschermt. Er mogen geen losse _commits_ geplaatst worden hierop. Enkel via een
merge.
- Het _rebasen_ is niet toegestaan. Er wordt enkel gebruik gemaakt van _merges_. Dit zorgt ervoor
dat er nooit iets kwijtgeraakt kan worden. 
- Doorgaans werkt er 1 persoon op een _feature branch_. Uitzonderingen bestaan zeker.
- _feature branches_ worden zo klein gehouden, dat ze beschreven kunnen worden in 1 alinea.
- _feature branches_ hebben minimaal goedkeuring nodig van 2 anderen (via een PR) 
om deze te mogen mergen. 
    - De laatste reviewer heeft hierbij toestemming om deze te mergen.
    - Enkel de eigenaar mag (na de merge) de _branch_ verwijderen.


## Planning 
Vanaf de design fase hebben we de keuze gemaakt _Github Projects_ te gaan gebruiken. Hiermee wordt
een zachte/vrije vorm van _SCRUM_ gebruikt. 
1. We delen de taken in globale modules. Zoals: "notificaties", "leningen" en "boetes".
2. De modules worden verdeeld over de teamsleden.
3. Elk teamslid stelt, voor zijn modules, de individuele taken vast. 
4. Het teamslid verwerkt de taken in de sprintplanning.

Dit proces vertrouwt heel erg op de teamsleden om grotendeels zelfstandig aan de slag te gaan. Zolang
ieder initiatief en discipline vertoont zal dit goed blijven gaan. Er is in die zin dus wel een hoge
verwachting, maar ook vertrouwen aan elkaar.


## Development omgeving
Er is een _docker_/_docker-compose_ omgeving opgezet met meerdere services om zo soepel de applicatie
te ontwikkelen. Deze services passen zich automatisch aan op veranderingen. 

### Testen
Zolang de omgeving draait wordt bij aanpassingen de passende service zijn unit tests opnieuw 
uitgevoerd.

Unit tests worden geschreven volgens de _TDD_ principes. Een uitzondering hiervan zijn prototypes 
en proof of concepts.
