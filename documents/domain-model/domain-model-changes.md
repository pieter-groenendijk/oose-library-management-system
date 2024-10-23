# Changes for domain model v2

## Leenhistorie
- **Correcte syntax toegepast**
- 1 leenhistorie heeft 0 ... 1 abonnement -> **1 leenhistorie heeft 1 ... 1 abonnement**. Het is nutteloos om een leenhistorie
aan te maken dat niet gekoppeld is aan een abonnement.
- 1 leenhistorie heeft 1 ... \*uitlening -> **1 leenhistorie heeft 0 ...\***. In de praktijk is het leenhistorie concept
puur wat records in de database. Als men dus nog niks heeft geleend is deze gewoon leeg.
- **Associaties verwoordingen verduidelijkt**