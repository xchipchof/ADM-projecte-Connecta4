# ADM-projecte-Connecta4
Projecte de l'assignatura ADM en kotlin

### UI separada dels components de navigació
La UI resideix a `conntecta4.ui` on es troben els components de la UI i les diferents pantalles per cada activitat, mentre que les activitats que implementen el flux de navegació estan separades. La UI sols pinta la pantalla amb els paràmetres que les activitats (emprant els respectius viewModels) els hi donin.
  
### Responsive i Lifecycle-aware
Les dades i lògica del joc s'emmagatzema al respectiu viewModel de l'activitat, el qual permet que tot i quan es recrea l'aplicació no es perdi el progrés de la partida. També les partides són responsive, cosa que permet continuar jugant tot i que l'orientació de la pantalla canviï o que la resolució de la pantalla sigui menor. Empra BoxWithConstraints per tal de fixar el content (el taulell del joc) segons una mida calculada abans de la composició del taulell (*dimPx**).

PD: Tant el logo de l'aplicació com el logo que es mostra en el launch de l'app és una imatge generada mitjançant IA generativa a partir d'un sol prompt i res més, per la qual cosa queda exempta de drets d'autor.
