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
                                <em>Usecase 'Betalen' begint.</em><br>
                                9. Systeem voegt het gekozen abonnement toe aan het account.<br>
                                10. Systeem toont het abonnement successvol is aangeschaft.<br>
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
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Abonnement kon niet worden toegevoegd</div>
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
                                9.B Systeem kan gekozen abonnement niet toevoegen aan account.<br>
                                10.B Systeem toont foutmelding: "Interne fout. Toevoegen abonnement gefaalt. Neem contact op."<br>
                            </td>
                        </tr>
                    </tbody>
                </table> 
            </td>
        </tr>
    </tbody>
</table>