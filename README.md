# Weather App
Acest proiect a fost realizat in cadrul laboratorului 3 de sisteme distribuite.
Aceasta aplicatie simuleaza un serviciu meteo si vom realiza refactorizari pentru a o imbunatati.
Structura initiala:
-- de adaugat diagrama din laborator
In cadrul acestui laborator, vom lucra cu containerul Spring, cu o structura alcatuita din controllere, interfete si servicii.
Ne vom concentra pe respectarea principiilor SOLID, intelegerea notiunilor:
*Inversarea dependentelor: Utilizarea interfetelor (dependenta de interfete) si nu a implemetarilor acestora -> decuplarea serviciilor.
    Ex: Vom impleemnta TimeInterface pentru a decupla serviciul de prognoza de ceasul sistemului.
*Open/Closed Principle (Deschis la dezvoltare, Inchis la modificari): permite adaugarea de noi furnizori meteo fara a modifica controller-ul
*Segregarea interfectelor: Interfete atomice pentru cautarea locatiei si prognoza.