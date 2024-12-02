# Betalen 
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
            <td></td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>Een lid moet kunnen betalen om bijv. een nieuw abonnement aan te schaffen.</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>Het bedrag is vastgesteld.</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>Het bedrag is betaald. De betaling is geregistreerd.</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Failure</th>
            <td>De mislukt poging is geregistreerd.</td>
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
                                1. Lid verzoekt de betaling te doen.<br>
                            </td>
                            <td>
                                2. Systeem registreert poging.<br>
                                3. Systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen.<br>
                                4. Systeem geeft aan dat de gebruiker doorverwezen kan worden naar het externe betaalsysteem.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                5. Lid verzoekt doorverwezen te worden.
                            </td>
                            <td>
                                6. Systeem verwijst de gebruiker door naar het externe betaalsysteem.<br>
                                7. Systeem wacht voor de bevestiging van extern betaalsysteem. Systeem ontvangt bevestiging binnen 3 seconden.<br>
                                8. Systeem verwerkt de betaling.<br>
                                9. Systeem toont dat de betaling is voltooid.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
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
                                3.B. Systeem vertelt het externe betaalsysteem dat het lid het bedrag gaat betalen. 
                                Systeem ontvangt geen reactie na 3 seconden. Systeem stopt met proberen.<br>
                                4.B Systeem registreert mislukte poging voor verdere opvolging.<br>
                                5.B Lid krijgt het bericht: "Het abonnement kan momenteel niet aangeschaft worden - Probeer het later opnieuw. <br>
                                <em>Terug naar stap 1.</em>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>Mislukte registratie poging</div>            
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
                                2.E Systeem mislukt registratie poging.<br>
                                3.E Systeem toont foutmelding: "Interne fout. Systeem kan voorbereiding niet voltooien".<br>
                                <em>Terug naar stap 1</em><br>
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
                                7.C Systeem ontvangt bevestiging van extern betaalsysteem dat de betaling mislukt is.
                                Systeem geeft een foutmelding aan dat de betaling mislukt is, bijvoorbeeld "Geen Saldo".<br>
                                <em>Terug naar stap 1.</em><br>
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
                                8.D Systeem geeft een foutmelding: "De betaling kon niet verwerkt worden. Interne fout. Neem contact op."<br>
                                9.D Systeem registreert mislukte verwerking.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>     
            </td>
        </tr>
    </tbody>
</table>
