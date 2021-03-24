## Pakiet [`wt.muppety.authentication`](../../src/main/java/wt/muppety/authentication)

### Lista klas:

* [`Authenticator`](../../src/main/java/wt/muppety/authentication/Authenticator.java)
* [`LoginData`](../../src/main/java/wt/muppety/authentication/LoginData.java)
* [`Permission`](../../src/main/java/wt/muppety/authentication/LoginData.java)

#### Klasa [`Authenticator`](../../src/main/java/wt/muppety/authentication/Authenticator.java)

Główna klasa zarządzająca kontrolująca uprawnienia do korzystania z systemu. Udostępnia funkcje służące do logowania 
i wylogowania użytkownika oraz sprawdzania uprawnień zalogowanego użytkownika. Korzysta ze wzorca Singleton.

#### Klasa [`LoginData`](../../src/main/java/wt/muppety/authentication/LoginData.java)

Klasa przechowująca dane logowania.

#### Enumerable [`Permission`](../../src/main/java/wt/muppety/authentication/LoginData.java)

Obiekty reprezentujące poszczególne uprawnienia dostępne w systemie:

* `canBuy` oznaczający możliwość zawierania transakcji z dostawcami
* `canSell` oznaczający możliwość zapisywania transakcji sprzedaży leków
* `canBrowseDB` oznaczający możliwość przeglądania bazy danych systemu
* `canModerateDB` oznaczający możliwość edytowania danych innych niż transakcje