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
            <td><strong>Main Success Scenario (Basic Flow)</strong></td>
            <td><strong>Actor Action</strong><br>1. De beheerder navigeert naar de pagina met beheerdersinstellingen<br>3. De beheerder bekijkt de instellingen zoals ze nu zijn. <br>4. De beheerder geeft aan de instellingen te willen wijzigen. <br>6. De beheerder past een of meerdere instellingen aan. <br>7. De beheerder geeft aan dat hij de instellingen op wil slaan.<br> 9. De beheerder bevestigt dat hij de instellingen op wil slaan. <br> <strong>System Responsibility</strong><br>2. Het systeem toont de huidige instellingen. <br>5. Het systeem maakt de instelling wijzigbaar. <br>8. Het systeem vraagt om bevestiging aan de gebruiker.<br>9. Het systeem valideert de ingave succesvol.<br>11. Het systeem registreert de instellingen.<br>12. Het systeem maakt de instellingen niet wijzigbaar. <br></td>
        </tr>
        <tr>
            <td><strong>Alternate Flows</strong></td>
            <td>7.A. De beheerder wil de instellingen niet opslaan --&gt; Verder bij stap 3.<br> 9.A. Invoer niet valide. --&gt; Verder bij stap 5.</td>
        </tr>
            <tr>
            <td><strong>Exceptional Flows</strong></td>
        </tr>
    </tbody>
</table>
