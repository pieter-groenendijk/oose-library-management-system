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
                                7.A Systeem stelt foutieve persoonsgegevens en/of abonnementstype vast.<br>
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
                                8.B Systeem geeft een foutmelding aan dat schuld momenteel niet betaald kan worden. 
                                Lid wordt geadviseerd contact op te nemen.
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
                                12.C Systeem geeft een foutmelding aan dat de betaling mislukt is.
                                Lid wordt geadviseerd wanneer deze denkt dat er iets is fout gegaan contact op te nemen.
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
                                13.D Systeem geeft een foutmelding aan dat de betaling niet verwerkt kon worden.
                                Lid wordt geadviseerd contact op te nemen.
                            </td>
                        </tr>
                    </tbody>
                </table>     
            </td>
        </tr>
    </tbody>
</table>