# Pharmacy App

### Grupa: Paweł Kopel, Konrad Przewłoka, Marek Ślązak, Magdalena Badura

#### **Milestone 1 (stan na 01.12.2020)**

- model danych
- plany integracji z bazą danych (Hibernate, SQLite)
- GUI pozwalające na dodawanie, edytowanie i usuwanie pracowników i produktów oraz dodawanie kategorii produktów

#### **Milestone 2 (stan na 15.12.2020)**

- autentykacja
- integracja z bazą danych (Hibernate, SQLite)
- GUI pozwalające na dodawanie transakcji, dostawców

## Opis

Aplikacja wspomagająca zarządzanie apteką. Aplikacja umożliwia pracownikom zarządzanie produktami dostępnymi w aptece,
zawieranymi transakcjami, jak i również listą pracowników.

## Technologie

* Java 14, Gradle
* Hibernate
* SQLite

## Uruchamianie aplikacji

Do uruchomienia aplikacji potrzebny jest program Gradle i Java 14.

Uruchomienie aplikacji — wywołanie komendy w katalogu głównym:

* Linux:

```
./gradlew run
```

* Windows:

```
gradlew.bat run
```

## Przewodnik po projekcie

#### [Model](docs/model/README.md)

#### [Persystencja](docs/persistence/README.md)

#### [Autentykacja](docs/authentication/README.md)

#### [GUI](docs/gui/README.md)

W projekcie wykorzystano wzorce projektowe:

* model-view-presenter [(GUI)](docs/gui/README.md)
* singleton [(autentykacja)](docs/authentication/README.md)
* DAO i ORM [(persystencja)](docs/persistence/README.md)

## Aplikacja

Po uruchomieniu aplikacji otwiera się okno logowania:

![gui_login](docs/pictures/login.png)

Do logowania trzeba wykorzystać dane użytkownika znajdującego się już w bazie, np. login 'user' hasło 'user'.

Po przejściu do Employee list otwiera się widok na listę pracowników z danymi: imię, nazwisko i pozycja. Mamy opcję
dodania pracownika oraz usunięcia/edytowania wpisu z listy.

![gui_employee_list](docs/pictures/employee_list.png)

Po wybraniu opcji Add uruchamia się okno Add user z polami z klasy Employee do wypełnienia. Po wybraniu opcji Edit
uruchamia się okno Edit user z polami wstępnie wypełnionymi. Po wybraniu konkretnego wpisu na liście aktywuje się opcja
Delete i wpis jest usuwany z listy. Widok po dodaniu jednego pracownika:

![gui_add_employee](docs/pictures/add_user.png)

Po przejściu do Product list otwiera się widok na listę produktów z danymi: nazwa, cena, kategoria, wytwórca,
informacja, czy lek jest na receptę oraz dostawca. Mamy opcję dodania/usunięcia/edytowania produktu, dodania kategorii
oraz dodania dostawcy do listy.

![gui_product_list](docs/pictures/product_list.png)

Widok dodawania produktu:

![gui_add_product](docs/pictures/add_product.png)

Widok dodawania dostawcy:

![gui_add_supplier](docs/pictures/add_supplier.png)

Trzecią opcją w głównym oknie jest dodanie nowej transakcji:

![gui_add_transaction](docs/pictures/add_transaction.png)

Zamknięcie okna nowej transakcji powoduje otwarcie widoku listy produktów.

Widok strony głównej aplikacji przy zalogowanym pracowniku:

![1](docs/pictures/1.png)

Widok listy pracowników wraz z oknem dodawania pracownika przy zalogowanym pracowniku:

![2](docs/pictures/2.png)

Widok listy produktów wraz z oknem dodawania dostawcy przy zalogowanym pracowniku:

![3](docs/pictures/3.png)

Widok strony głównej wraz z oknem dodawania transakcji przy zalogowanym pracowniku:

![4](docs/pictures/4.png)

Widok strony głównej przy zalogowanym kliencie z aktywowaną subskrypcją:

![6](docs/pictures/6.png)

Widok listy produktów posortowanych według kategorii przy zalogowanym kliencie:

![8](docs/pictures/8.png)

Widok listy produktów przy użyciu opcji szukania po nazwie przy zalogowanym kliencie:

![7](docs/pictures/7.png)



