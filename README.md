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

#### **Milestone 3 (stan na 12.01.2021)**

- dodanie opcji subskrypcji i wysyłania notyfikacji przez maila
- dodanie sortowania i wyszukiwania w listach pracowników i produktów
- poprawienie opcji wybrania wielu kategorii przy dodawaniu/edycji produktu
- ulepszone GUI
- rozróżnienie widoku klienta i pracownika
- haszowanie haseł użytkowników w bazie danych

## Opis

Aplikacja wspomagająca zarządzanie apteką. Aplikacja umożliwia pracownikom zarządzanie produktami dostępnymi w aptece, zawieranymi transakcjami, jak i również listą pracowników.

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

#### [Notyfikator](docs/notificator/README.md)

W projekcie wykorzystano wzorce projektowe:

* Model-View-Presenter ([GUI](docs/gui/README.md))
* Singleton ([autentykacja](docs/authentication/README.md) i [powiadomienia](docs/notifications/README.md))
* DAO i ORM ([persystencja](docs/persistence/README.md))

## Aplikacja

**Aby zalogować się jako pracownik, należy wpisać**:
**login: test**
**password: test**

Po uruchomieniu aplikacji otwiera się okno logowania/rejestracji:

![5](docs/pictures/5.png)


Po wybraniu opcji Register otwiera się okno rejestracji:

![9](docs/pictures/9.png)

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



