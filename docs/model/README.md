## Pakiet [`wt.muppety.model`](../../src/main/java/wt/muppety/model)

### Lista klas:

* [`Category`](../../src/main/java/wt/muppety/model/Category.java)
* [`Employee`](../../src/main/java/wt/muppety/model/Employee.java)
* [`Permissions`](../../src/main/java/wt/muppety/model/Permissions.java)
* [`Product`](../../src/main/java/wt/muppety/model/Product.java)
* [`Supplier`](../../src/main/java/wt/muppety/model/Supplier.java)
* [`Transaction`](../../src/main/java/wt/muppety/model/Transaction.java)

#### Klasa [`Product`](../../src/main/java/wt/muppety/model/Product.java)

Klasa [`Product`](../../src/main/java/wt/muppety/model/Product.java) zawiera pola:

* ID — identyfikator (Primary Key)
* CategoryID — identyfikator kategorii produktu (Foreign Key)
* SupplierID — identyfikator dostawcy (Foreign Key)
* Name — nazwa produktu
* UnitPrice — cena produktu
* OnPrescription — flaga mówiąca o tym, czy lek jest na receptę
* Manufacturer — nazwa producenta

#### Klasa [`Category`](../../src/main/java/wt/muppety/model/Category.java)

Klasa [`Category`](../../src/main/java/wt/muppety/model/Category.java) zawiera pola:

* ID — identyfikator (Primary Key)
* Name — nazwa kategorii

#### Klasa [`Supplier`](../../src/main/java/wt/muppety/model/Supplier.java)

Klasa [`Supplier`](../../src/main/java/wt/muppety/model/Supplier.java) zawiera pola:

* ID — identyfikator (Primary Key)
* CompanyName — nazwa firmy
* Email — email do komunikacji z firmą
* PhoneNumber — numer telefonu do komunikacji z firmą

#### Klasa [`Transaction`](../../src/main/java/wt/muppety/model/Transaction.java)

Klasa [`Transaction`](../../src/main/java/wt/muppety/model/Transaction.java) zawiera pola:

* ID — identyfikator (Primary Key)
* ProductID — identyfikator kupowanego produktu (Foreign Key)
* EmployeeID — identyfikator pracownika nadzorującego transakcję (Foreign Key)
* Quantity — ilość kupowanych produktów
* Value — cena do zapłaty
* Date — data wykonania transakcji

#### Klasa [`Employee`](../../src/main/java/wt/muppety/model/Employee.java)

Klasa [`Employee`](../../src/main/java/wt/muppety/model/Employee.java) zawiera pola:

* ID — identyfikator (Primary Key oraz Foreign Key)
* Firstname — imię
* Lastname — nazwisko
* Position — pozycja w firmie: Manager, Chair lub Worker. To pole korzysta z typu `Employee.Position` do kontrolowania
  dozwolonych wartości
* Login — wykorzystywany przy autentykacji
* Password — wykorzystywane przy autentykacji

#### Klasa [`Permissions`](../../src/main/java/wt/muppety/model/Permissions.java)

Klasa [`Permissions`](../../src/main/java/wt/muppety/model/Permissions.java) zawiera pola:

* EmployeeID — identyfikator (Primary Key)
* CanSell — pozwolenie na sprzedawanie leków
* CanBuy — pozwolenie na kupowanie leków
* CanBrowseDatabase — pozwolenie na przeglądanie bazy danych
* CanModerateDatabase — pozwolenie na modyfikację bazy danych
