## Pakiet [`wt.muppety.notificator`](../../src/main/java/wt/muppety/notificator)

### Klasa [`Notificator`](../../src/main/java/wt/muppety/notificator/EmailNotificator.java)

Klasa odpowiadająca za wysyłanie maili z powiadomieniami o dostępności produktu. Pozwala na wysyłanie
wiadomości do konkretnego użytkownika lub do wszystkich użytkowników posiadających subskrypcję na dany
produkt. Implementuje wzorzec Singleton. Klasa wykorzystuje Java Mail API. Wysyła email poprzez protokół SMTP za 
pośrednictwem specjalnie utworzonego konta na gmailu.
