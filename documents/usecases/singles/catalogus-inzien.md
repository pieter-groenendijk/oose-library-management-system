# Catalogus Inzien

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
            <td>Bezoeker/Lid/Editor/Baliemedewerker</td>
        </tr>
        <tr>
            <td><strong>Stakeholders and Interests</strong></td>
            <td>Beheerder, Editor</td>
        </tr>
        <tr>
            <td><strong>Cross References</strong></td>
            <td>Requirement FR-005</td>
        </tr>
        <tr>
            <td><strong>Brief Description</strong></td>
            <td>Als gebruiker wil ik de catalogus kunnen doorzoeken.</td>
        </tr>
        <tr>
            <td><strong>Preconditions</strong></td>
            <td> De catalogus kan in eerste instantie leeg zijn als er nog geen producten zijn toegevoegd.<br>
                </td>
        </tr>
        <tr>
            <td><strong>Postconditions on Success</strong></td>
            <td>De gebruiker heeft resultaten uit de catalogus gezien of een melding ontvangen dat er geen resultaten zijn.</td>
        <tr>
            <td><strong>Postconditions on Failure</strong></td>
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
                                1. De bezoeker opent het catalogus.<br>
                               2. De bezoeker voert zoekcriteria in.<br>
							   3. De bezoeker start de zoekopdracht.<br>
                            </td>
                            <td><br><br><br>
                                4. Het systeem ontvangt de ingevoerde zoekcriteria en verwerkt deze, door de catalogus te doorzoeken op basis van de opgegeven filters.<br>
								5. Het systeem geeft de resultaten weer.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
        <tr>
       <tr>
            <th scope="row">Alternate Flow</th>
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
                              5.A: De bezoeker kiest er voor om de zoekopdracht te veranderen of te verfijnen. --> Verder bij stap 1.<br> <br>
                            </td> <td></td>
                        </tr>
                    </tbody>
                </table> 
            </td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
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
                            <td></td>
                            <td>
                          5.B Het systeem toont een melding "Geen resultaten gevonden voor de opgegeven zoekcriteria." --> Verder bij stap 1.
             </td>
                        </tr>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                          <tr>  
                            <td></td>
                            <td>
                        5.C Het systeem geeft geen resultaten terug aan de gebruiker. <br>
                        6.C Het systeem registreerd dat de gebruiker geen zoekresultaten heeft gezien. </td>
                        </tr>
            </table>
    </tbody>
</table>