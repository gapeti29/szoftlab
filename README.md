# szoftlab

A programkódban van pár eltérés az UML diagramokhoz képest.

1.: Random pályagenerálás.
Megcsináltam úgy a programot, hogy a pálya négyzet alapú legyen és random generálja rá a különböző dolgokat (munkás, lyuk, stb.). Ehhez a Warehouse osztályban vettem fel plusz függvényeket.
A generálás úgy kezdődik, hogy létrejön a négyzet alapú pálya, mely csak Field-eket tartalmaz. Ezután lesznek random kicserélve egyes mezők valami másra (pl: lyukra, kapcsolóra, vagy kerül rá egy láda, stb.). Minden olyan mező, mely már nem csak egy szimpla üres mező, az egy extra mező, erre már nem lehet majd generálni semmit. A Field egy változóban tárolja, hogy ő extra mező-e.
Az InitXXX() függvények felelősek létrehozni az XXX dolgot, ahol XXX a Worker, Crate, stb.
A ChangeField() függvények kicserélnek egy mezőt a pályában. Az új mező eltárolja a szomszédait, plusz a vele szomszédos mezők is eltárolják őt.
A pályát egy 2 dimenziós tömbbe raktam, hogy könnyebb legyen kezelni.

2.: Érvényes lépések figyelése.
A Warehouseban létrehoztam egy Crate[] tömböt, mely eltárolja a játékban lévő ládákat.
A Warehouse HasMoves() függvénye minden kör elején ellenőrzi, hogy van-e érvényes lépés a játékban. Ehhez meghívja a játékosok, valamint a ládák HasMoves() függvényét, melyek egy boolean-nal jelzik, hogy tudnak-e lépni.
A munkások akkor tudnak lépni, ha a 4 irány közül bármerre tudnak lépni egyet. Ezt úgy ellenőrzik, hogy meghívják a mezőjuk CheckMove() függvényét, minden irányban.
A ládák akkor tudnak lépni, ha függőlegesen, vagy vízszintesen tudnak mozogni. Tehát egyszerre kell szabadnak lennie fel és le, vagy jobbra és balra az útnak. Ezt ugyanúgy a Field CheckMove() függvényével ellenőrzi.

3.: Mozgás
A Move() függvényt nem valósítja meg külön-külön a Worker és a Crate, hanem a MovableThing-ben írtam meg, mert ugyanazt csinálják.
