# Notificatieoverzicht inzien
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
            <td>FR-039, FR-040, FR-042, FR-044, FR-041, FR-043</td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>
                Het lid krijgt een overzicht te zien van de notificaties die de gebruiker heeft ontvangen. Hierbij ziet de gebruiker enkel een preview van elk. Het is zichtbaar welke nog niet zijn ingezien. Mogelijk zou de gebruiker een specifieke notificatie inzien. 
            </td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td></td>
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
                                1. Lid geeft aan zijn notificaties in te zien.<br>
                            </td>
                            <td>
                                2. Systeem haalt algemene gegevens op van de notificaties.<br>
                                3. Systeem presenteert toont de algemene gegevens van de opgehaalde notificaties.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <div>Notificatie inzien</div>           
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
                                4. Lid geeft aan een specifieke notificatie in te willen zien.<br>
                                <em>UC "Notificatie Inzien" is begonnen.</em>
                            </td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div> Fout bij het laden van notificaties: </div>           
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
                                2.A Het systeem geeft aan geen notificaties te kunnen laden momenteel. Probeer het later nog eens. → Verder naar stap 1.
                            </td>
                        </tr>
                    </tbody>
                </table>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
            </td>
        </tr>
    </tbody>
</table>
