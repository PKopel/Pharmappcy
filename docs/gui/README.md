## Pakiet [`wt.muppety.controller`](../../src/main/java/wt/muppety/controller)

### Lista klas:

* [`AbstractController`](../../src/main/java/wt/muppety/controller/AbstractController.java)
* [`AppController`](../../src/main/java/wt/muppety/controller/AppController.java)
* [`MainViewController`](../../src/main/java/wt/muppety/controller/MainViewController.java)
* [`EmployeeListController`](../../src/main/java/wt/muppety/controller/EmployeeListController.java)
* [`ProductListController`](../../src/main/java/wt/muppety/controller/ProductListController.java)
* [`LoginViewController`](../../src/main/java/wt/muppety/controller/LoginViewController.java)

#### Klasa [`AbstractController`](../../src/main/java/wt/muppety/controller/AbstractController.java)

Klasa zawierająca podstawowe metody kontrolera pozwalające na ładowanie danych do GUI i powrót do poprzedniego widoku.

#### Klasa [`AppController`](../../src/main/java/wt/muppety/controller/AppController.java)

Główna klasa odpowiadająca za ładowanie GUI. Zawiera dwie generyczne metody pozwalające na zmianę układu okna aplikacji
i otwarcie okna dialogu. Obydwie metody ładują dane przekazane jako argumenty do kontrolerów otwieranych widoków.

#### Klasa [`MainViewController`](../../src/main/java/wt/muppety/controller/MainViewController.java)

Klasa kontrolująca widok głównego okna aplikacji. Korzystając z klasy [`Authenticator`](../authentication/README.md)
ustala zakres działań dostępny dla aktualnie zalogowanego pracownika. Umożliwia przejście do list produktów i
pracowników oraz otwarcie okna nowej transakcji. Klasa
[`MainViewController`](../../src/main/java/wt/muppety/controller/MainViewController.java)
korzysta z układu zdefiniowanego w pliku [MainViewPane.fxml](../../src/main/resources/view/MainViewPane.fxml).

#### Klasa [`EmployeeListController`](../../src/main/java/wt/muppety/controller/EmployeeListController.java)

Klasa kontrolująca widok list pracowników. Zawiera metody pozwalające na dodawanie, usuwanie i edytowanie zawartości
tabeli [`Employee`](../persistence/README.md). Korzysta z klasy [`Authenticator`](../authentication/README.md)
do kontroli uprawnień zalogowanego użytkownika. Klasa
[`EmployeeListController`](../../src/main/java/wt/muppety/controller/EmployeeListController.java)
korzysta z układu zdefiniowanego w pliku [EmployeeListPane.fxml](../../src/main/resources/view/EmployeeListPane.fxml).

#### Klasa [`ProductListController`](../../src/main/java/wt/muppety/controller/ProductListController.java)

Klasa kontrolująca widok list produktów. Zawiera metody pozwalające na dodawanie, usuwanie i edytowanie zawartości
tabeli [`Product`](../persistence/README.md) oraz dodawanie nowych kategorii produktów. Klasa
[`ProductListController`](../../src/main/java/wt/muppety/controller/ProductListController.java)
korzysta z układu zdefiniowanego w pliku [ProductListPane.fxml](../../src/main/resources/view/ProductListPane.fxml).

#### Klasa [`LoginViewController`](../../src/main/java/wt/muppety/controller/LoginViewController.java)

Klasa dziedzicząca po klasie
[`AbstractController`](../../src/main/java/wt/muppety/controller/AbstractController.java)
umożliwiająca użytkownikom rejestrację oraz logowanie do systemu.

## Pakiet [`wt.muppety.presenter`](../../src/main/java/wt/muppety/presenter)

### Lista klas:

* [`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)
* [`EditCategoryDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditCategoryDialogPresenter.java)
* [`EditEmployeeDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditEmployeeDialogPresenter.java)
* [`EditProductDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditProductDialogPresenter.java)
* [`EditSupplierDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditSupplierDialogPresenter.java)
* [`EditTransactionDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditTransactionDialogPresenter.java)

#### Klasa [`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)

Klasa [`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java) zawiera
podstawowe metody i pola potrzebne do obsługi okna dialogu — akceptacji bądź odrzucenia wprowadzonych danych oraz
uzupełnienia interfejsu danymi z bazy aplikacji.

#### Klasa [`EditCategoryDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditCategoryDialogPresenter.java)

Klasa dziedzicząca po klasie
[`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)
specjalizowana w obsłudze dialogu dodawania nowej kategorii.

#### Klasa [`EditEmployeeDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditEmployeeDialogPresenter.java)

Klasa dziedzicząca po klasie
[`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)
specjalizowana w obsłudze dialogu dodawania lub edycji wpisu pracownika.

#### Klasa [`EditProductDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditProductDialogPresenter.java)

Klasa dziedzicząca po klasie
[`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)
specjalizowana w obsłudze dialogu dodawania lub edycji produktu.

#### Klasa [`EditSupplierDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditSupplierDialogPresenter.java)

Klasa dziedzicząca po klasie
[`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)
specjalizowana w obsłudze dialogu dodawania lub edycji wpisu dostawcy.

#### Klasa [`EditTransactionDialogPresenter`](../../src/main/java/wt/muppety/presenter/EditTransactionDialogPresenter.java)

Klasa dziedzicząca po klasie
[`AbstractDialogPresenter`](../../src/main/java/wt/muppety/presenter/AbstractDialogPresenter.java)
specjalizowana w obsłudze dialogu dodawania lub edycji transakcji.

## Pakiet [`wt.muppety.view`](../../src/main/java/wt/muppety/view)

#### Enumerable [`LayoutName`](../../src/main/java/wt/muppety/view/LayoutName.java)

Obiekt zapewniający kontrolę nad wartościami przekazywanymi jako ścieżki do plików
['.fxml'](../../src/main/resources/view).

#### Klasa [`DynamicComboBoxPane`](../../src/main/java/wt/muppety/view/DynamicComboBoxPane.java)

Rozszerzenie klasy `javafx.scene.layout.GridPane` dynamicznie tworzące wiersze zawierające 
`ComboBox` i przycisk do stworzenia kolejnego wiersza.