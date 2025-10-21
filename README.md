# Wzorzec Obserwator

Przykład ilustrujący budowę i wykorzystanie wzorca *Obserwator* (ang. _Observer_).

**Przy okazji**:
- wstrzykiwanie zależności
- dostęp do webowych API przez HTTP(S)
- format JSON i jego podstawowe wykorzystanie z poziomu Javy
- cykliczne wykonywanie operacji (scheduler)

**Uwaga dotycząca terminologii**: w różnych opracowaniach na temat tego wzorca pojawiają się różne terminy na
wstępujące w nim elementy. Klasycznie pojawia się para _subject - observer_. Można się też spotkać
z _observable - observer_ czy _publisher - subscriber_. Ja stosuję w opisie ostatnią z możliwości.


## Zadanie
Zadanie polega na zaprojektowaniu i zaimplementowaniu aplikacji, która będzie pozwalała
na śledzenie ceny wybranych metali szlachetnych z wykorzystaniem zewnętrznego źródła danych.
W tym opisie będę się posługiwał przykładem _platyny_, ale można się skupić na dowolnym metalu,
którego cena jest publikowana w API.

Zakładamy, że dane pobieramy co kilka sekund. Przede wszystkim wyświetlamy aktualną cenę. 
Te same dane chcemy zapisywać w pliku logu.
W ostatnim etapie dodajemy moduł statystyk, który też ma być powiadamiany o cenie. Integracja ma być jak najprostsza.

Podsumowując: trzeba zaproponować rozwiązanie gotowe na rozszerzanie i rozbudowę, a także łatwe w testowaniu.


### Wymagania
**Poziom 3**
- aplikacja jest zbudowana na bazie obserwatora; jest zdefiniowana **klasa** `Publisher` oraz **interfejs** `Subscriber`
  oraz zaimplementowany główny mechanizm wzorca
- w stałych odstępach czasu (np. 3 sekundy) wyświetla **aktualną cenę** platyny
- na tym poziomie dane są sztuczne (są losowane w jakimś realistycznym zakresie)
- informacje o pobranych danych są również zapisywane w pliku logu

**Poziom 4**
- typ danych w ramach obserwatora jest parametryzowany (umożliwienie wykorzystania w innym miejscu)
- istnieje możliwość przekazania źródła danych (poprzez wstrzykiwanie zależności)
- podczas wyświetlania i w logu należy uwzględnić aktualną cenę platyny oraz czas notowania
- działa pobieranie faktycznych danych z API za pomocą protokołu HTTP (np. [goldapi.io](https://www.goldapi.io))

**Poziom 5**
- dodać klasę statystyk, która jest subskrybentem; ma wyznaczać na bieżąco kilka statystycznych wielkości
  związanych z ceną
- stworzyć zestaw testów jednostkowych sprawdzających funkcjonowanie statystyk
- cykliczne działania są zrealizowane jako zadanie pracujące w osobnym wątku
  (sugerowane zastosowanie klasy `ScheduledExecutorService`)
- informacje o aktualnej cenie mają się wyświetlać cały czas (tak jak było od początku),
  ale statystyki mają się wyświetlić po wciśnięciu klawisza Enter
 