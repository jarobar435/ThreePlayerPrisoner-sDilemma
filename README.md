# Three-player prisoner dilemma
Implementation based on rules presented in Zbigniew Michalewicz's book - "Algorytmy genetyczne + struktury danych = programy ewolucyjne" (1996).

## Schemat algorytmu:

- [x] Wybierz populację. Każdemu graczowi przyporządkowuje się losowo łańcuch 521 bitów reprezentujący strategię.
- [X] Sprawdź każdego gracza, aby określić jego efektywność. Każdy gracz używa strategii zapisanej w swoim chromosomie aby rozgrywać gry z pozostałymi graczami. Wynik gracza jest średnią ze wszystkich rozegranych przez niego gier.
- [X] Wybierz graczy do rozmnożenia. Gracz ze średnim wynikiem dostaje jednego partnera do skojarzenia, gracz z wynikiem o jedno odchylenie standardowe lepszym dostaje dwóch partnerów, a gracz z wynikiem o jedno odchylenie standardowe gorszym od średniego nie dostaje żadnego partnera.
- [x] Wygrywający gracze są losowo kojarzeni w celu utworzenia dwóch potomków z jednej pary. Strategia potomków jest tworzona ze strategii rodziców. Do tego służą dwa operatory genetyczne - krzyżowanie i mutacja.

## Parowanie:
Szablon algorytmu dostępny w src/.../utils/TestKombinacji
```
Załóżmy zbiór 6 elementowy: {a, b, c, d, e, f}
Dla dwóch graczy mamy parowanie:
a-b
a-c b-c
a-d b-d c-d
a-e b-e c-e d-e
a-f b-f c-f d-f e-f
 = 15 gier 1vs1

Dla trzech graczy:
a-b-c
a-b-d a-c-d
a-b-e a-c-e a-d-e
a-b-f a-c-f a-d-f a-e-f

b-c-d
b-c-e b-d-e
b-c-f b-d-f b-e-f

c-d-e
c-d-f c-e-f

d-e-f
 = 20 gier 1vs1vs1 
 = 20 kombinacji 3 elementowych bez powtórzeń ze zbioru 6 elementowego
 ```
 
 ## Określanie decyzji więźnia
 Dla uproszczenia poniższego przykładu przyjmijmy, że chromosom składa się z dwóch tablic binarnych  
 (w naszej implementacji jest to ArrayList z enum Decision gdzie 0: BETRAYED, 1:COLLABORATED)
  
 * bity 0-8 (lista lastThreeMoves) -> ostatnie 3 ruchy 3 graczy  
 * bity 9-520 (lista strategy) -> unikalna strategia gracza 
 ```
 załóżmy, że ostatnie 3 'rozgrywki' więźnia A wyglądaly następująco:
 	1	, 	2	,	3	
 A: BETRAYED (b0), BETRAYED	, COLLABORATED
 B: BETRAYED (b1), COLLABORATED	, BETRAYED
 C: BETRAYED (b2), BETRAYED	, BETRAYED
 
 na ich podstawie wybrany zostanie kolejny ruch 'A':
 
 b0 = 0 -> {rozpatrujemy bity 9-264}
 	b1 = 0 -> {bity 9-136}
 	b1 = 1 -> {bity 137-264}
 b0 = 1 -> {rozpatrujemy bity 265-520}
 	b1 = 0 -> ...
 	b1 = 1 -> ...
 
 9 takich podziałów podziałów pozwoli okreslić ruch do wykonania w następnej rundzie
 1: 512/2
 2: 256/2
 3: 128/2
 4: 64/2
 5: 32/2
 6: 16/2
 7: 8/2
 8: 4/2
 9: 2/2 -> wskazanie na konkretny bit z decyzją
```
## Obliczanie średniej punktacji więźnia
Średni wynik = suma uzyskanych punktów / liczba parowań z konkretnym więźniem * liczba gier w parach.
W programie nie określamy jawnie w ilu rozgrywkach każdy więzień bierze udział,  
stąd pojawia się potrzeba obliczenia tej wartości.

```
Liczba kombinacji k-elementowych ze zbioru n-elementowego bez powtórzeń,
w których występuje konkretna wartość.
Ponieważ jeden element jest określony, szukamy (k-1) elementów,
bez powtórzeń - więc element ten umniejsza zbiór do rozmiaru (n-1).

(n-1)! / (k-1)! * (n-k)!
```
Jako, że populacja wielkości 50 osobników generowała wynik silni (63-cyfrowy) przekraczający zakres long-a,  
uprościliśmy wzór korzystając z naszej stałej liczby k-elementów (więźniów):
```
(n-1)! / (3-1)! * (n-3)!

(n-2) * (n-1) / 2
```
