# Pharmacy App - aplikacja do zarządzania apteką
### Grupa: Paweł Kopel, Konrad Przewłoka, Marek Ślązak, Magdalena Badura

#### **Milestone 1 (stan na 01.12.2020)** 

## Technologie
Java 14, Gradle
Hibernate

## Uruchamianie aplikacji
Do uruchomienia aplikacji potrzebny jest program gradle i java 14.
Aplikację można uruchomić komendą `./gradlew run` (linux) albo `gradlew.bat run` (windows)
wykonaną w głównym katalogu aplikacji.

## Model bazy danych

[db_model](db_model.png)

Product zawiera:
* CategoryID
* SupplierID
* Name
* UnitPrice
* OnPrescription - flaga mówiąca o tym, czy lek jest na receptę
* Manufacturer

Category zawiera:
* ID
* Name

Supplier zawiera:
* ID
* CompanyName
* Email
* PhoneNumber

Transaction zawiera:
* ID
* ProductID
* EmployeeID
* Quantity
* Value
* Date

Employee zawiera:
* ID
* Firstname
* Lastname
* Position
* Login
* Password

Permissions zawiera:
* EmployeeID
* CanSell
* CanBuy
* CanBrowseDatabase
* CanModerateDatabase
