## Feedback Design 21-11<br>
### Vragen en opmerkingen aan P: <br>
- hoe kun je een lening of reservering verbinden aan het notificatie systeem<br>
- Zorg er voor dat je teamgenoten je diagrammen ook begrijpen<br>
- Wees waakzaam voor overengineering. Breng het terug naar de basis. KISS<br>
- Wat gebeurd er als je reccuring taken hebt in dit systeem?<br>
- Gebruik naamgeving die correspondeert met Java naamgeving.<br>
- Studiegenoot: Hoe kom je bij een notificatie?<br>
- Studiegenoot: Heb je een factory die factories aanmaakt?<br>

### Vragen en opmerkingen voor L: <br>
* Abonnementsbeheer: 3 calls naar DB. Docent:Je kunt rustig 3 DB calls doen om in verschillende stappen te kunnen reageren. Als je 1x naar je DB gaat, krijg je 1x antwoord. Ligt aan je flow. <br>
- In de flow van Abonnementsbeheer is 3x een db aanspraak niet erg. Student: Ik zou het zo laten. Docent: Qua overhead maakt dit niet uit. Voor Transacties zou ik dit niet doen. Voor transacties zou ik die aan de java kant beginnen. <br>
- MembershipType ophalen uit DB? Wat geeft de database terug? Gebruik een ORM mapper, niet zelf alle code gaan schrijven, een ORM mapper scheelt enorm.<br>
- DTO, zet je alleen dingen in die voor de aanroepende partij relevant zijn. <br>
- Verwerk ook validatie in Class/Sequence diagrams <br>
- Test classes moeten in het class diagram staan. Accountservice kunnen testen met mocking. <br>
- Concentreer op service en op repositorylaag voor Testen <br>
- Er komt een request binnen/api binnen bij de controller (json). De controller draait in een framework, en daar zet je DTO's in.<br>
- Framework voor DTO. Welk framework ga je aan de java kant gebruiken? Kijk naar Spring, daar kom je al een heel eind mee.<br>
  
### Vragen en opmerkingen aan M:<br>
- Deel je Class Diagram verder op in: leningen aanmaken, leningen retourneren, leningen terug naar de schap.<br>
- Ga daarna pas ieder onderdeel modelleren.<br>
- Bedenk dat er nog veel meer stappen tussen kunnen zitten en modelleer deze ook.<br>

### TODO <br>
- Feedback verwerken<br>
- Domein Model updaten<br>
- Class Diagrams updaten<br>
- SSD updaten<br>
- Feedback binnen de groep op diagrammen<br>
