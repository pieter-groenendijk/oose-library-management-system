# Product inzien
<table> 
    <thead>
        <tr>
            <th scope="col" colspan="2">Section</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">Primary Actor</th>
            <td>Bezoeker</td>
        </tr>
        <tr>
            <th scope="row">Stakeholders & Interests</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Cross References</th>
            <td>FR-006</td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>Als bezoeker(of andere hogere rol), wil ik informatie kunnen inzien van een specifiek product.</td>
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
                            <td>1. Bezoeker verzoekt een specifiek product in te zien.</td>
                            <td>
                                2. Systeem haalt gegevens op van het specifieke product.<br>
                                3. Systeem presenteert gegevens.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <div>Fysiek product reserveren</div>           
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
                                4b. Systeem weet dat het product niet in bezit is van deze gebruiker en niet gereserveerd is.
                                De mogelijkheid om te reserveren wordt gepresenteerd.
                            </td>
                        </tr>
                        <tr>
                            <td>5b. Bezoeker verzoekt product te reserveren. Usecase <em>Fysiek Product Reserveren</em> begint.</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div>Fysiek product verlengen</div>           
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
                                4a. Systeem weet dat het product in bezit is van deze gebruiker. De mogelijkheid om te
                                verlengen wordt gepresenteerd.
                            </td>
                        </tr>
                        <tr>
                            <td>5a. Bezoeker verzoekt product te verlengen. Usecase <em>Fysiek Product Verlengen</em> begint.</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div>Digitaal product toevoegen aan account</div>           
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
                                4c. Systeem weet dat het product digitaal beschikbaar is. De mogelijkheid om het product 
                                toe te voegen aan zijn account wordt gepresenteerd.
                            </td>
                        </tr>
                        <tr>
                            <td>5c. Bezoeker verzoekt het product toe te voegen aan zijn account. Usecase <em>Digitaal Product Toevoegen Aan Account</em> begint.</td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Gegevensbron onbereikbaar of geen gegevens beschikbaar</div>           
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
                                2d. Systeem kan gegevens niet verkrijgen.<br>
                                3d. Systeem presenteert foutmelding dat de gegevens niet verkregen konden worden.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>
