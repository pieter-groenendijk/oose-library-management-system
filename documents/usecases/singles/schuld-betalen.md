# Boete Betalen
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
            <td>Als lid, wil ik mijn boetes kunnen betalen.</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>1. Heeft een openstaande boete</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>1. De boete(s) is betaald. Er is geen openstaande boete.<br></td>
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
                            <td>1. Lid verzoekt om de boete te betalen.</td>
                            <td>
                                2. Systeem haalt informatie van de boete op.<br>
                                3. Systeem presenteert een lijst van boetes en een totaalbedrag.<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                4. Lid verzoekt de openstaande boete af te lossen.<br>
                                <em>Usecase 'Betalen' begint.</em><br>
                            </td>
                            <td>
                                5. Systeem zet lid zijn schuld op nul.<br>
                                6. Systeem geeft aan dat de schuld succesvol is betaald.
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
                                2.A Systeem geeft een foutmelding aan dat de openstaande boete(s) niet opgehaald kan worden.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>
