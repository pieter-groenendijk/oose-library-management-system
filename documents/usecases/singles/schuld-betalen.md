# Schulden (gedeeltelijk) Betalen
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
            <th scope="row">Stakeholders & Interests</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Cross References</th>
            <td>FR-034</td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>
                Het lid kiest een bedrag en dient deze gegevens in. Het systeem valideert de opgegeven gegevens. 
                Het systeem begeleidt de gebruiker vervolgens door het betaalproces</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>Heeft een openstaande schuld</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>De schuld is met het opgegeven bedrag verlaagt.</td>
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
                            <td>1. Het lid verzoekt om zijn schuld te betalen.</td>
                            <td>
                                2. Het systeem haalt informatie van de schuld op.<br>
                                3. Het systeem presenteert een lijst van boetes en een totaalbedrag.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                4. Het lid geeft het bedrag aan om af te lossen.<br>
                                5. Het lid geeft aan gegevens te willen verzenden.
                            </td>
                            <td>
                                6. Het systeem valideert het aangegeven getal.<br>
                                7. Het systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen.<br>
                                8. Het systeem geeft aan dat de gebruiker doorverwezen kan worden naar het externe betaalsysteem.
                            </td>
                        </tr>
                        <tr>
                            <td>
                                9. Het lid geeft aan doorverwezen te willen worden.
                            </td>
                            <td>
                                10. Het systeem verwijst de gebruiker door naar het externe betaalsysteem.<br> 
                                11. Het systeem wacht voor bevestiging van extern betaalsysteem.<br>
                                12. Het systeem verwerkt de betaling.<br>
                                13. Het systeem toont dat de betaling is voltooid.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <div>Gehele bedrag aflossen</div>           
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td>4.A Het lid geeft aan het gehele bedrag te willen af te lossen.</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div>Ongeldig bedrag opgeleverd</div>
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
                            <td>6.A Het systeem stelt een foutief opgegeven bedrag vast. <em>terug naar 4</em></td>
                        </tr>
                    </tbody>
                </table> 
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Schuldinformatie onbereikbaar</div>
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
                                2.A Systeem geeft een foutmelding aan dat de schuld niet opgehaald kon worden.
                                Lid wordt geadviseerd contact op te nemen. 
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>Geen openstaande schuld</div>  
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
                            <td>3.A Het systeem geeft aan dat er niks te betalen is.</td>
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
                                7.A Het systeem geeft een foutmelding aan dat schuld momenteel niet betaald kan worden. 
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
                                11.A Het systeem geeft een foutmelding aan dat de betaling mislukt is.
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
                                12.A Het systeem geeft een foutmelding aan dat de betaling niet verwerkt kon worden.
                                Lid wordt geadviseerd contact op te nemen.
                            </td>
                        </tr>
                    </tbody>
                </table> 
            </td>
        </tr>
    </tbody>
</table>
