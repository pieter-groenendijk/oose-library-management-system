# Product Toevoegen Aan Catalogus
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
            <td>Editor</td>
        </tr>
        <tr>
            <td><strong>Stakeholders and Interests</strong></td>
            <td>Beheerder, Bezoeker</td>
        </tr>
        <tr>
            <td><strong>Cross References</strong></td>
            <td>Requirement FR-008</td>
        </tr>
        <tr>
            <td><strong>Brief Description</strong></td>
            <td>Als content beheerder wil ik nieuwe producten kunnen toevoegen aan de bibliotheekcatalogus.</td>
        </tr>
        <tr>
            <td><strong>Preconditions</strong></td>
            <td>1. De content beheerder is ingelogd in het bibliotheekbeheersysteem.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Success</strong></td>
            <td>1. Het product is toegevoegd aan de catalogus.<br>2. Het product is zichtbaar voor bezoekers van de bibliotheekcatalogus.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Failure</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Main Success Scenario (Basic Flow)</strong></td>
            <td><strong>Actor Action</strong><br>1. De editor gaat nagiveert naar de locatie voor het toevoegen van een nieuwe product. <br> 3. De Editor vult de gegevens van het nieuwe product in. <br> 4. De editor bevestigt de invoer.<br> <strong>System Responsibility</strong><br>2.Het systeem toont een formulier met velden zoals titel, auteur/maker, publicatiedatum, beschrijving, producttype, en beschikbaarheid.<br> 5.Het systeem valideert de ingevoerde gegevens (bijvoorbeeld verplichte velden zijn ingevuld). <br> 6.Bij een succesvolle validatie slaat het systeem het product op in de catalogus en bevestigt de toevoeging.</td>
        </tr>
        <tr>
            <td><strong>Alternate Flows</strong></td>
            <td>6.A. Het systeem heeft een validatiefout. Als verplichte velden niet zijn ingevuld of de gegevens ongeldig zijn, geeft het systeem een foutmelding en vraagt om correcties. Ga terug naar stap 3.
            <br><br> 6.B Het systeem heeft geeft een melding dat dit product al aanwezig is in de catalogus. Ga terug naar stap 3.
</td>
        </tr>
        <tr>
            <td><strong>Exceptional Flows</strong></td>
            <td>3.A  De editor annuleert de invoer. Het systeem annuleert de invoer en keert terug naar de catalogus zonder het product toe te voegen.</td>
        </tr>
    </tbody>
</table>
