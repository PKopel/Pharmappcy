## Pakiet `wt.muppety.model`

### Lista klas:

* `Category`
* `Employee`
* `Permissions`
* `Product`
* `Supplier`
* `Transaction`
* `EmployeePosition`

#### Klasa `Product`

Klasa `Product` zawiera pola:

* ID — identyfikator (Primary Key)
* CategoryID — identyfikator kategorii produktu (Foreign Key)
* SupplierID — identyfikator dostawcy (Foreign Key)
* Name — nazwa produktu
* UnitPrice — cena produktu
* OnPrescription — flaga mówiąca o tym, czy lek jest na receptę
* Manufacturer — nazwa producenta

#### Klasa `Category`

Klasa `Category` zawiera pola:

* ID — identyfikator (Primary Key)
* Name — nazwa kategorii

#### Klasa `Supplier`

Klasa `Supplier` zawiera pola:

* ID — identyfikator (Primary Key)
* CompanyName — nazwa firmy
* Email — email do komunikacji z firmą
* PhoneNumber — numer telefonu do komunikacji z firmą

#### Klasa `Transaction`

Klasa `Transaction` zawiera pola:

* ID — identyfikator (Primary Key)
* ProductID — identyfikator kupowanego produktu (Foreign Key)
* EmployeeID — identyfikator pracownika nadzorującego transakcję (Foreign Key)
* Quantity — ilość kupowanych produktów
* Value — cena do zapłaty
* Date — data wykonania transakcji

#### Klasa `Employee`

Klasa `Employee` zawiera pola:

* ID — identyfikator (Primary Key oraz Foreign Key)
* Firstname — imię
* Lastname — nazwisko
* Position — pozycja w firmie: Manager, Chair lub Worker. To pole korzysta z typu `Employee.Position` do kontrolowania
  dozwolonych wartości
* Login — wykorzystywany przy autentykacji
* Password — wykorzystywane przy autentykacji

#### Klasa `Permissions`

Klasa `Permissions` zawiera pola:

* EmployeeID — identyfikator (Primary Key)
* CanSell — pozwolenie na sprzedawanie leków
* CanBuy — pozwolenie na kupowanie leków
* CanBrowseDatabase — pozwolenie na przeglądanie bazy danych
* CanModerateDatabase — pozwolenie na modyfikację bazy danych
