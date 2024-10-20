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
            <th scope="row">Main Success Scenario (Basic Flow)</th>
            <td>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                    <td><br>
                1. De editor navigeerd naar de locatie voor het toevoegen van een nieuw product.<br></td> 
                <td>
                2. Het systeem toont een formulier met velden zoals titel, auteur, ISBN, beschrijving en producttype.</td>
                <tr><td>
                3. De editor vult de velden voor het nieuwe product.<br>
                4. De editor bevestigd de invoer.</td>
                <td>
                    5. Het systeem valideert de invoer (bijvoorbeeld verplichte velden zijn ingevuld).<br>
                    6. Het systeem voegt het product toe aan de catalogus.<br>
                    7. Het systeem toont een bevestiging van de toevoeging.</td>
                </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td><td>
                             6.A Het systeem heeft een validatiefout. Als verplichte velden niet zijn ingevuld of de gegevens ongeldig zijn, geeft het systeem een foutmelding en vraagt om correcties. -->Verder naar stap 3.   <br><br>
                            </td> 
                        </tr>
		        <table>
                 <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td><td>
           6.B Het systeem heeft geeft een melding dat dit product al aanwezig is in de catalogus. --> Verder naar stap 3.
                            </td> 
                        </tr>
                    </tbody></table>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td>
                         3.A  De editor annuleert de invoer. </td> 
<td>4.A Het systeem annuleert de invoer en keert terug naar de catalogus zonder het product toe te voegen.</td>
                        </tr>
    </tbody></table>
</table></table>
