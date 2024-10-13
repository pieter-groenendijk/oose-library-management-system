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
            <td></td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>
                Men krijgt een overzicht te zien van de notificaties die de gebruiker heeft ontvangen. Hierbij ziet de gebruiker enkel een preview van elk. Het is zichtbaar welke nog niet zijn ingezien. Mogelijk zou de gebruiker een specifieke notificatie inzien. 
            </td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>Gelezen notificaties zijn gemarkeerd als _gelezen_</td>
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
                                1. Het lid geeft aan zijn notificaties in te zien.<br>
                            </td>
                            <td>
                                2. Het systeem haalt algemene gegevens op van de notificaties<br>
                                3. Het systeem presenteert previews van de opgehaalde notificaties<br>
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
                                4. Het lid geeft aan een specifieke notificatie in te willen zien.<br>
                                <em>UC "Notificatie Inzien" is begonnen.</em>
                            </td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div>Notificatieinstellingen inzien</div>           
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
                                4. Het lid geeft aan de notificatieinstellingen te willen inzien<br>
                                <em>UC "Notificatieinstellingen inzien" is begonnen.</em>
                            </td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Notificatieinstellingen inzien</div>           
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
                                4. Systeem kan niet succesvol de notificaties ophalen. Systeem presenteert een representatieve foutmelding weer.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>
