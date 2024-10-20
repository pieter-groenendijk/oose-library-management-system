# Beheerdersinstellingen Beheren
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
            <td>Beheerder</td>
        </tr>
        <tr>
            <td><strong>Stakeholders and Interests</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Cross References</strong></td>
            <td>Requirement FR-035, FR-036, FR-037, FR-038</td>
        </tr>
        <tr>
            <td><strong>Brief Description</strong></td>
            <td>Als beheerder wil ik instellingen kunnen inzien/aanpassen</td>
        </tr>
        <tr>
            <td><strong>Preconditions</strong></td>
            <td>De gebruiker moet ingelogd zijn als beheerder in het bibliotheekbeheersysteem.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Success</strong></td>
            <td>1. De beheerder heeft de huidige instellingen bekeken. <br>2. De beheerder heeft de instellingen naar wens aangepast.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Failure</strong></td>
            <td>1. De beheerder heeft de instellingen niet aangepast.</td>
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
                            <td>
                                1. De beheerder navigeert naar de pagina met beheerdersinstellingen<br><br>
                                3. De beheerder bekijkt de instellingen zoals ze nu zijn.<br> </td>
                           <td><br>
                                2. Het systeem toont de huidige instellingen. <br></td>
                        <tr>  
                            <td>4. De beheerder geeft aan de instellingen te willen wijzigen. <br> </td>
                        <td> 5. Het systeem toont de instellingen die aangepast kunnen worden. <br> </tr>
                        <tr> <td>   
                                6. De beheerder past een of meerdere instellingen aan. <br> 
                                7. De beheerder geeft aan dat hij de instellingen op wil slaan.<br>
                                9. De beheerder bevestigt dat hij de instellingen op wil slaan. <br>
                            </td>
                        </tr> 
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <td><strong>Alternate Flows</strong></td>
            <td>7.A. De beheerder wil de instellingen niet opslaan --&gt; Verder bij stap 3.<br> <br>
                9.A. Het systeem geeft aan dat de invoer niet valide is. --&gt; Verder bij stap 6.</td>
        </tr>
            <tr>
            <td><strong>Exceptional Flows</strong></td>
            <td>9.B. Systeemfout bij laden van instellingen: Als het systeem niet in staat is om de huidige instellingen te laden (bijvoorbeeld door een serverfout of databaseconnectieprobleem), dan wordt er een foutmelding getoond aan de beheerder, en de beheerder kan geen aanpassingen maken. --&gt; Verder bij stap 1. <br><br>
                9.C. Systeemfout bij opslaan van instellingen: Als het systeem niet in staat is om de instellingen op te slaan (bijvoorbeeld door een serverfout of databaseconnectieprobleem), dan wordt er een foutmelding getoond aan de beheerder, en de beheerder kan geen aanpassingen maken. --&gt; Verder bij stap 3. <br><br>
                9.D. Systeemfout bij valideren van invoer: Als het systeem niet in staat is om de invoer te valideren (bijvoorbeeld door een serverfout of databaseconnectieprobleem), dan wordt er een foutmelding getoond aan de beheerder, en de beheerder kan geen aanpassingen maken.--&gt; Verder bij stap 3.</td>
</table>
