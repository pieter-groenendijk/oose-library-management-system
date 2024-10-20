# Digitaal Product Toevoegen Aan Account
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
            <td>Bezoeker</td>
        </tr>
        <tr>
            <td><strong>Stakeholders and Interests</strong></td>
            <td>Beheerder</td>
        </tr>
        <tr>
            <td><strong>Cross References</strong></td>
            <td>Requirement FR-027</td>
        </tr>
        <tr>
            <td><strong>Brief Description</strong></td>
            <td>Als lid wil ik een digitaal tijdschrift kunnen lezen.</td>
        </tr>
        <tr>
            <td><strong>Preconditions</strong></td>
            <td>1. Het lid is ingelogd en heeft een geldig abonnement.<br>2. Het systeem beschikt over een collectie van digitale tijdschriften die kunnen worden uitgeleend.<br>3. Het lid heeft geen boetes of restricties die het lenen van items verhinderen.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Success</strong></td>
            <td>1. Het digitale tijdschrift is succesvol uitgeleend aan het lid.<br>2. Het lid heeft toegang tot het tijdschrift voor de geldende leentermijn.<br>3. Het lid ontvangt een bevestigingsmail met uitleen- en toegangsinformatie.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Failure</strong></td>
            <td>1. Het digitale tijdschrift is niet uitgeleend aan het lid.<br>2. Het lid heeft geen toegang tot het digitale tijdschrift.<br>3. Het systeem toont een foutmelding met de reden van het falen (bijv. tijdschrift niet beschikbaar, lid niet in orde).<br>4. Geen uitleenactie wordt geregistreerd.</td>
        </tr>
        <tr>
            <td><strong>Main Success Scenario (Basic Flow)</strong></td>
            <td><strong>Actor Action</strong><br>1. Het lid logt in op het systeem en navigeert naar de bibliotheekcatalogus.<br>2. Het lid zoekt een digitaal tijdschrift in de catalogus.<br>4. Het lid selecteert een tijdschrift dat beschikbaar is voor uitleen.<br>6. Het lid bevestigt dit tijdschrift te lenen.<br>9. Het lid krijgt een melding dat het tijdschrift succesvol is uitgeleend en heeft nu toegang tot het digitale tijdschrift.<br>10. Het lid ontvangt een bevestiging met de uitleengegevens en instructies voor het lezen van het digitale tijdschrift.<br> <strong>System Responsibility</strong><br>3. Het systeem toont de resultaten van de zoekopdracht, inclusief de beschikbare digitale tijdschriften.<br>5. Het systeem toont de details van het tijdschrift, inclusief de leentermijn en eventuele uitleenvoorwaarden.<br>7. Het systeem controleert de lidstatus en controleert of het lid het tijdschrift mag lenen (bijv. geen boetes of restricties).<br>8. Het systeem registreert de uitleenactie en markeert het tijdschrift als uitgeleend aan het lid.</td>
        </tr>
        <tr>
            <td><strong>Alternate Flows</strong></td>
            <td>5.A. Als het tijdschrift niet beschikbaar is (bijvoorbeeld als het al is uitgeleend):<br>&nbsp;&nbsp;&nbsp;&nbsp;5.A.1. Het systeem toont een melding dat het tijdschrift niet beschikbaar is en biedt de mogelijkheid om een reservering te plaatsen.<br>7.A. Als het lid niet voldoet aan de uitleenvoorwaarden (bijv. openstaande boetes):<br>&nbsp;&nbsp;&nbsp;&nbsp;7.A.1. Het systeem toont een foutmelding en vraagt het lid om de situatie op te lossen (bijv. betalen van boetes).<br>9.A. Als de uitleenactie mislukt door een systeemfout:<br>&nbsp;&nbsp;&nbsp;&nbsp;9.A.1. Het systeem toont een foutmelding en vraagt het lid om het opnieuw te proberen.</td>
        </tr>
        <tr>
            <td><strong>Exceptional Flows</strong></td>
            <td>3.A. Als er geen tijdschriften beschikbaar zijn:<br>&nbsp;&nbsp;&nbsp;&nbsp;3.A.1. Het systeem toont een melding dat er geen resultaten zijn of biedt een suggestie voor andere beschikbare items.<br>7.A. Als het lid niet voldoet aan de uitleenvoorwaarden (bijv. openstaande boetes):<br>&nbsp;&nbsp;&nbsp;&nbsp;7.A.1. Het systeem toont een foutmelding en vraagt het lid om de situatie op te lossen (bijv. betalen van boetes).<br>9.A. Als de uitleenactie mislukt door een systeemfout:<br>&nbsp;&nbsp;&nbsp;&nbsp;9.A.1. Het systeem toont een foutmelding en vraagt het lid om het opnieuw te proberen.</td>
        </tr>
    </tbody>
</table>
