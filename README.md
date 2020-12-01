# Pharmacy App - aplikacja do zarządzania apteką
### Grupa: Paweł Kopel, Konrad Przewłoka, Marek Ślązak, Magdalena Badura

#### **Milestone 1 (stan na 01.12.2020)** 

## Technologie
* Java 14, Gradle
* Hibernate

## Uruchamianie aplikacji
Do uruchomienia aplikacji potrzebny jest program Gradle i Java 14.

Uruchomienie aplikacji - wywołanie komendy w katalogu głównym:
* Linux:
```
./gradlew run
```

* Windows:
```
gradlew.bat run
```


## Model bazy danych

![db_model](db_model.png)

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


## Przewodnik po projekcie
[Data Access Object](./src/main/java/wt/muppety/dao)

[Model](./src/main/java/wt/muppety/model) - klasy Category, Employee, MockData, Product, Supplier, Transaction

GUI zgodne ze wzorcem model-view-presenter:

* [Controller](./src/main/java/wt/muppety/controller)
* [Presenter](./src/main/java/wt/muppety/presenter)
* [View](./src/main/java/wt/muppety/view)



## Aplikacja

Po uruchomieniu aplikacji mamy opcję przejścia do listy pracowników lub do listy produktów:

![gui_1](gui_1.png)

Po przejściu do Employee list mamy widok na listę pracowników z danymi: imię, nazwisko i pozycja. Mamy opcję dodania pracownika oraz usunięcia/edytowania wpisu z listy.

![gui_2](gui_2.png)

Po przejściu do Product list mamy widok na listę produktów z danymi: nazwa, cena, kategoria, wytwórca oraz informacja, czy lek jest na receptę. Mamy opcję dodania produktu, dodania kategorii oraz usunięcia/edytowania wpisu z listy.

![gui_3](gui_3.png)
