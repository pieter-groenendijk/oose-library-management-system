# Schulden Betalen
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
            <td>Als lid, wil ik mijn openstaande schuld afbetalen.</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>1. Heeft een openstaande schuld</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>1. De schuld is afgelost. Er is geen openstaande schuld<br></td>
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
                            <td>1. Lid verzoekt om zijn schuld te betalen.</td>
                            <td>
                                2. Systeem haalt informatie van de schuld op.<br>
                                3. Systeem presenteert een lijst van boetes en een totaalbedrag.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                4. Lid verzoekt de openstaande schuld af te lossen.<br>
                            </td>
                            <td>
                                5. Systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen.<br>
                                6. Systeem geeft aan dat de gebruiker doorverwezen kan worden naar het externe betaalsysteem.
                            </td>
                        </tr>
                        <tr>
                            <td>
                                7. Lid geeft aan doorverwezen te willen worden.
                            </td>
                            <td>
                                8. Systeem verwijst de gebruiker door naar het externe betaalsysteem.<br> 
                                9. Systeem wacht voor bevestiging van extern betaalsysteem.<br>
                                10. Systeem verwerkt de betaling.<br>
                                11. Systeem toont dat de betaling is voltooid.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td></td>
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
                                7.B Systeem geeft een foutmelding aan dat schuld momenteel niet betaald kan worden. 
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
                                11.C Systeem geeft een foutmelding aan dat de betaling mislukt is.
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
                                12.D Systeem geeft een foutmelding aan dat de betaling niet verwerkt kon worden.
                                Lid wordt geadviseerd contact op te nemen.
                            </td>
                        </tr>
                    </tbody>
                </table> 
            </td>
        </tr>
    </tbody>
</table>
