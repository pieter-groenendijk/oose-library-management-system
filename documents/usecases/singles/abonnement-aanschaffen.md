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
                                7. Lid bevestigt keuze.<br>
                            </td>
                            <td>
                                8. Systeem valideert de opgegeven persoonsgegevens en abonnementstype keuze.<br>
                                9. Systeem registreert poging tot abonnement aanschaf op.<br>
                                10. Systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen.<br>
                                11. Systeem geeft aan dat de gebruiker doorverwezen kan worden naar het externe betaalsysteem.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                12. Lid verzoekt doorverwezen te worden.
                            </td>
                            <td>
                                13. Systeem verwijst de gebruiker door naar het externe betaalsysteem.<br>
                                14. Systeem wacht voor bevestiging van extern betaalsysteem.<br>
                                15. Systeem verwerkt de betaling.<br>
                                16. Systeem toont dat de betaling is voltooid.
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
                                8.A Systeem stelt foutieve persoonsgegevens en/of abonnementstype vast. <br>
                                9.A Systeem communiceert fout.<br>
                                <em>terug naar stap 2 of 6 (afhankelijk van fout)</em>
                            </td>
                        </tr>
                    </tbody>
                </table> 
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
                                10.B. Systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen. 
                                Systeem ontvangt geen reactie na 3 seconden. Systeem stopt met proberen.<br>
                                11.B Systeem registreert mislukte poging voor verdere opvolging.<br>
                                12.B Lid krijgt het bericht: "Het abonnement kan momenteel niet aangeschaft worden - Probeer het later opnieuw. <br>
                                <em>Terug naar stap 6.</em>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>Mislukte registratie poging tot abonnement aanschaf</div>            
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
                                9.E Systeem mislukt tot registreren van poging to abonnement aanschaf.<br>
                                10.E Systeem toont foutmelding: "Interne fout. Systeem kan voorbereiding niet voltooien".<br>
                                <em>Terug naar stap 6</em><br>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>Mislukte betaling betaalsysteem</div>
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
                                14.C Systeem ontvangt bevestiging van extern betaalsysteem dat de betaling mislukt is.
                                Systeem geeft een foutmelding aan dat de betaling mislukt is, bijvoorbeeld "Geen Saldo".<br>
                                <em>Terug naar stap 12.</em><br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
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
                                15.D Systeem geeft een foutmelding: "De betaling kon niet verwerkt worden. Interne fout. Neem contact op."<br>
                                16.D Systeem registreert mislukte verwerking.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>     
            </td>
        </tr>
    </tbody>
</table>