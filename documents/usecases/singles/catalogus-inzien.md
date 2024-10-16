# Abonnement Aanschaffen
<table>
    <thead>
        <tr>
            <th scope="col" colspan="2">Section</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">Primary Actor</th>
            <td>Lid</td>
        </tr>
        <tr>
            <th scope="row">Stakeholders and Interests</th>
            <td>Beheerder, Baliemedewerker</td>
        </tr>
        <tr>
            <th scope="row">Cross References</th>
            <td>Requirement FR-003</td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>Als bezoeker wil ik een volwassen abonnement kunnen aanschaffen via het systeem.</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>1. De bezoeker heeft een lidmaatschap bij de bibliotheek.<br>2. Het lid is geauthenticeerd en geautoriseerd.</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>1. Het abonnement is gekoppeld aan het lidmaatschap.</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Failure</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Main Success Scenario (Basic Flow)</th>
            <td>Actor Action<br>1. Het lid gaat naar het systeem waar het abonnementsinformatie kan inzien.<br>2. Het lid selecteert ‘Volwassen abonnement’.<br>4. Het lid kiest om het abonnement aan te schaffen.<br>6. Het lid vult de gegevens in.<br>9. Het lid kiest een betaalmethode.<br>11. Het lid ontvangt een bevestiging met de abonnementsgegevens.<br> <strong>System Responsibility</strong><br>3. Het systeem toont de prijs en details van het abonnement.<br>5. Het systeem vraagt om de persoonsgegevens van de bezoeker. (Naam, adres, e-mail, etc.)<br>7. Het systeem valideert de ingegeven gegevens. <br>8. Het systeem toont de beschikbare betaalmethoden (extern systeem).<br>10. Het systeem verifieert de betalingsgegevens en betaling (extern systeem), en verifieert de succesvolle aanschaf van het abonnement.</td>
        </tr>
        <tr>
            <th scope="row">Alternate Flows</th>
            <td>2.A Het lid kiest een abonnement voor enkel digitale producten.</td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>9.A. Het lid besluit het proces te annuleren en keert terug naar Stap 1. <br> 10.A. Extern betalingssysteem niet beschikbaar.</td>
        </tr>
    </tbody>
</table>