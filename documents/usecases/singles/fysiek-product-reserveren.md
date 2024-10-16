# Fysiek Product Reserveren
<table>
    <thead>
        <tr>
            <th><strong>Section</strong></th>
            <th><strong>Details</strong></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><strong>Primary Actor</strong></td>
            <td>Lid</td>
        </tr>
        <tr>
            <td><strong>Stakeholders and Interests</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Cross References</strong></td>
            <td>Requirement FR-030</td>
        </tr>
        <tr>
            <td><strong>Brief Description</strong></td>
            <td>Als lid wil ik een fysiek product kunnen reserveren.</td>
        </tr>
        <tr>
            <td><strong>Preconditions</strong></td>
            <td>1. Het lid is geautoriseerd.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Success</strong></td>
            <td>1. De bezoeker heeft een reservering gemaakt voor het gekozen media uit de catalogus.<br>2. Het aantal beschikbare exemplaren van het media ter reservering is verminderd.<br>3. De bezoeker heeft een bericht ontvangen wanneer deze reservering verloopt.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Failure</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Main Success Scenario (Basic Flow)</strong></td>
            <td><strong>Actor Action</strong><br>1. De bezoeker doorzoekt de catalogus en selecteert het gewenste media.<br>3. De bezoeker kiest om het media te reserveren.<br>11. De bezoeker ontvangt een notificatie met wanneer de reservering beschikbaar is en hoe lang de bezoeker gebruik kan maken van de reservering.<br>12. De bezoeker verlaat het systeem.<br> <strong>System Responsibility</strong><br>2. Het systeem toont de beschikbaarheid van het media.<br>4. Het systeem controleert of de lid aan de reserveringsvoorwaarden voldoet.<br>5. Het systeem registreert de reservering aan het ledenaccount.<br>6. Het systeem wijst een uiterste reservering verbruik dag aan.<br>7. Het systeem vermindert het aantal beschikbare exemplaren van het item in de catalogus.<br>8. Het systeem geeft een notificatie wanneer de reservering beschikbaar is.</td>
        </tr>
        <tr>
            <td><strong>Alternate Flows</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Exceptional Flows</strong></td>
            <td>3.A. Het systeem geeft aan dat de bezoeker het maximum aantal reserveringen heeft bereikt. Ga door naar stap 10.<br>4.B. Het systeem geeft aan dat de bezoeker geen reservering kan maken tenzij een bestaande reservering wordt geannuleerd. Ga door naar stap 10.<br>6.A. Het systeem geeft een melding dat de bezoeker eerst de openstaande boetes moet betalen voordat er een reservering kan plaatsvinden.</td>
        </tr>
    </tbody>
</table>


