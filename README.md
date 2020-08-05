# Instrukcja 
Do poprawnego działania aplikacji wymagana jest poprawnie zainstalowana Java w wersji 11.
Projekt zawiera maven wrapper dzięki czemu maven nie jest wymagant.

## Konfiguracja
W pliku application.yml znajduje się konfiguracja, która definiuje endpoint serwisu NBP, który zwraca kursy walut.
```
nbp:
  url: http://api.nbp.pl/api/exchangerates/tables/
currency:
    tables: a,b
```
Poza tym mamy również możliwość ustawienia czasu odświeżania kursów:

```
  cron:
    expression: 0 0 12 * * ?
```

## Budowanie aplikacji

```
mvnw clean package
```
 
## Uruhcamianie aplikacji
```
mvnw spring-boot:run
```
Przykładowe zapytanie przeliczające walutę z PLN do EUR

```
http://localhost:8080/api/convert?currency=PLN&value=100&targetCurrency=EUR
```
