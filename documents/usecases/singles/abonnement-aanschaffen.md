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
            <td>Als lid wil ik een abonnement kunnen aanschaffen via het systeem.</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>
                1. Het lid is geautoriseerd.
            </td>
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
                                1. Lid verzoekt een abonnement aan te schaffen.<br>
                                2. Lid selecteert abonnementstype.<br>
                            </td>
                            <td>
                                3. Systeem toont de prijs en details van het abonnement.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                4. Lid bevestigt keuze.<br>
                            </td>
                            <td>
                                5. Systeem vraagt om de persoonsgegevens van de bezoeker.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                6. Lid vult zijn persoonsgegevens in.<br>
                            </td>
                            <td>
                                7. Systeem valideert de opgegeven persoonsgegevens en abonnementstype keuze.<br>
                                8. Systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen.<br>
                                9. Systeem geeft aan dat de gebruiker doorverwezen kan worden naar het externe betaalsysteem.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                10. Lid verzoekt doorverwezen te worden.
                            </td>
                            <td>
                                11. Systeem verwijst de gebruiker door naar het externe betaalsysteem.<br>
                                12. Systeem wacht voor bevestiging van extern betaalsysteem.<br>
                                13. Systeem verwerkt de betaling.<br>
                                14. Systeem toont dat de betaling is voltooid.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <div>Foutieve persoonsgegevens en/of abonnementstype</div>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td>
                            <td>
                                7.A Systeem stelt foutieve persoonsgegevens en/of abonnementstype vast. <br>
                                8.A Systeem communiceert fout.<br>
                                <em>terug naar stap 2 of 6 (afhankelijk van fout)</em>
                            </td>
                        </tr>
                    </tbody>
                </table> 
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Fout extern betaalsysteem</div>            
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td>
                            <td>
                                8.B Het systeem toont een "loading" bericht en probeert in de achtergrond opnieuw verbinding te maken met het betaalsysteem.<br>
                                9.B Lid krijgt een bericht dat het abonnement momenteel niet aangeschaft kan worden - Probeer het later opnieuw. <br>
                                10.B  Het systeem registreert het mislukte aanschafpoging in de logbestanden voor verdere opvolging.<br>
                                11.B   De aanvraagprocedure voor het abonnement wordt geannuleerd, en er worden geen kosten in rekening gebracht.<br>
</td>
                        </tr>
                    </tbody>
                </table>
                <div>Gefaalde betaling betaalsysteem</div>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td>
                            <td>
                                12.C Systeem geeft een foutmelding aan dat de betaling mislukt is, bijvoorbeeld "Geen Saldo".  <br>
                                13.C De gebruiker heeft de keuze om het opnieuw te proberen of te annuleren. Terug naar 12.C <br>
                                13.C De aanvraag procedure voor het abonnement wordt geannuleerd.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>Mislukte verwerking betaling</div>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td>
                            <td>
                                13.D Systeem geeft een foutmelding aan dat de betaling niet verwerkt kon worden. <br>
                                14.D Het systeem toont een melding aan de gebruiker dat de betaling is mislukt en geeft gedetailleerde informatie over de fout (bijvoorbeeld "Interne fout, betaling kan momenteel niet worden voltooid").<br>
                                15.D Het systeem annuleert de betalingspoging en zorgt ervoor dat er geen geld van de gebruiker wordt afgeschreven.<br>
                                16.D Alle relevante details van de mislukte transactie worden gelogd voor verdere diagnose en probleemoplossing.<br>
                                17.D  De gebruiker wordt teruggeleid naar de vorige pagina (Stap 3), waar hij/zij opnieuw een betaling kan proberen zodra het systeem hersteld is.
                            </td>
                        </tr>
                    </tbody>
                </table>     
            </td>
        </tr>
    </tbody>
</table>