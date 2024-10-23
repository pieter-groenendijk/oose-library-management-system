# Beheerdersinstelling Instellen
<table> 
    <thead>
        <tr>
            <th scope="col" colspan="2">Section</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">Primary Actor</th>
            <td>Beheerder</td>
        </tr>
        <tr>
            <th scope="row">Stakeholders & Interests</th>
            <td>Lid</td>
        </tr>
        <tr>
            <th scope="row">Cross References</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
            <td>Er is een beheerdersinstelling geselecteerd.</td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>De geselecteerde beheerdersinstelling heeft nu de nieuwe opgegeven waarden.</td>
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
                                3. Beheerder voert nieuwe waarde(n) in.<br>
                                4. Beheerder bevestigt de wijziginen.<br>
                            </td>
                            <td>   
                                1. Systeem haalt huidige waarden op
                                2. Systeem toont huidige waarden.
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                5. Systeem valideert de opgegeven nieuwe waarde(n).<br>
                                6. Systeem slaat de nieuwe waarde(n) op.<br>
                                7. Systeem bevestigt naar de beheerder dat de instellingen zijn bijgewerkt.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <div>Nieuwe waarde(n) opslaan mislukt</div>           
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
                                6C. Systeem kan nieuwe waarde(n) niet opslaan.<br>
                                7C. Systeem geeft door dat de nieuwe waarde(n) niet opgeslagen konden worden.<br>
                                Terug naar stap 4<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>Invalide nieuwe waarde(n)</div>           
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
                                5B. Systeem stelt invalide nieuwe waarde(n) vast.<br>
                                6B. Systeem geeft door dat de nieuwe waarde(n) invalide zijn.<br>
                                Terug naar stap 3<br> 
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Huidige waarden niet beschikbaar</div>           
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
                                1A. Systeem kan huidige waarden niet ophalen.<br>
                                2A. Systeem geeft door aan de beheerder dat de huidige waarden niet opgehaald konden worden.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>
