3 variantas 
Naudojant alarm manager, notification 

sukurti programa kuri nustatytu tam tikru laiko momentu (nors ir hardcoded laiko momentu) issiuncia notification, kad atejo numatytas laikas. Jei irendinys miega, alarm manager turi ji pazadinti

1. sukurti programa su viena pagrindine veikla, kurioje butu mygtukas paleidziantis AlarmManager, kuris is anksto uzduotu laiko momentu issiuncia transliacija apie tai ,kad numatytas laikas atejo. Notifikacijoje pasirodo pranesimas nurodantis, kad numatytas laikas atejo (+ icon turi rodyt prie notification)

3. (?)  Jei ekranas miega reikia ji pazadinti ir vis tiek parodyt pranesima

4. Kartu su pranesimu programa turi duoti garsini signala, nusakanti kad numatytas laikas atejo. Paspaudus ant pranesimo turi buti atidaryta kita veikla su raudonu fonu ir 2 mygtukais. Paspaudus viena mygtuka garsinis signalas isjungiamas, bet AlarmManager turi likti aktyvus. Paspaudus 2 mygtuka uzdaromos visos programos (taip pat ir visi AlarmManager)