# Fysiek Product Reserveren
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
            <td>Lid</td>
        </tr>
        <tr>
            <td><strong>Stakeholders and Interests</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Cross References</strong></td>
            <td>Requirement FR-030</td>
        </tr>
        <tr>
            <td><strong>Brief Description</strong></td>
            <td>Als lid wil ik een fysiek product kunnen reserveren.</td>
        </tr>
        <tr>
            <td><strong>Preconditions</strong></td>
            <td>1. Lid is geautoriseerd.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Success</strong></td>
            <td>
                1. De bezoeker heeft een reservering gemaakt voor het gekozen product uit de catalogus.<br>
                2. Het aantal beschikbare exemplaren van het product ter reservering is verminderd.<br>
                3. De bezoeker zal een bericht ontvangen wanneer deze reservering verloopt.</td>
        </tr>
        <tr>
            <td><strong>Postconditions on Failure</strong></td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Main Success Scenario (Basic Flow)</strong></td>
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
                                1. Lid verzoekt product te reserveren.<br>
                                2. Lid bevestigt keuze.<br>
                            </td>
                            <td>
                                3. Systeem controleert of het lid aan de reserveringsvoorwaarden voldoet.<br>
                                4. Systeem registreert de reservering.<br>
                                5. Systeem weergeeft het bijhorende reserveer-termijn.<br>
                                6. Systeem plant een notificatie wanneer de reservering beschikbaar is.<br>
                                7. Systeem stuurt notificatie om lid te informeren wanneer de reservering beschikbaar zal zijn.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <td><strong>Alternate Flows</strong></td>
            <td>   <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td>2.A Het lid kiest ervoor om de reservering te annuleren nadat het verzoek is ingediend.</td>
                            <td>3.A Het systeem annuleert de reservering en toont een bevestiging van de annulering.
                            </td>
                        </tr>
                    </tbody>
                </table></td>
        </tr>
        <tr>
            <td><strong>Exceptional Flows</strong></td>
            <td>
                <div>Voldoet niet aan reserveringsvoorwaarden</div>           
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
                                3.A Systeem stelt vast dat lid niet voldoet aan reserveringsvoorwaarden. Het lid krijgt 
                                een passende foutmelding te zien.
                            </td>
                        </tr>
                    </tbody>
                </table>
      <div>Het lid heeft incomplete contactgegevens geregistreerd, waardoor de notificatie niet kan worden verstuurd.</div>           
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
                                3.A Het systeem waarschuwt de gebruiker dat de contactinformatie moet worden bijgewerkt om notificaties te ontvangen. <br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>


