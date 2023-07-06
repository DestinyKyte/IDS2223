# Loyalty Platform
## una piattaforma per il customer care.

### Processo
Il processo si articola in tre iterazioni della durata media di due settimane ciascuna.
Nella prima iterazione, durante molteplici sessioni di brain storming, si è analizzata la specifica del cliente, stilando l'elenco degli attori coinvolti e di tutti i casi d'uso.\
Per ogni iterazione:
  1) sono stati selezionati i casi d'uso su cui lavorare in base alla strategia risk driven;
  2) i casi d'uso selzionati sono stati dettagliati, producendo il loro use case diagram;
  3) sono stati prodotti i relativi sequence diagrams;
  4) è stato prodotto il class diagram di analisi;
  5) è stato prodotto il class diagram di progetto, dettagliando quello di analisi;
  6) si è fatta un'ulteriore selezione dei principali casi d'uso da implementare;
  7) i casi d'uso selezionati sono stati implementati.

Nella prima iterazione è stata posta maggiore enfasi nella fase di analisi, specialmente per quanto riguarda l'individuazione dei casi d'uso.
Nell'ultima iterazione la parte implementativa è stata preponderante.\
Il processo è stato documentato tramite numerosi git commits.


### Analisi

Nel file di testo "lista attori e casi d'uso", sono presenti tutti i casi d'uso individuati mentre nel file vpp solo quelli selezionati al passo 1.
Qui viene descritto in che modo il titolare possa creare un negozio, un programma fedeltà (a punti, a cashback, a livelli, premium e consorzio), una campagna marketing e come possa pubblicarla. Inoltre, viene descritta la creazione di profili specifici per ogni tipologia di dipendente e in che modo si possa assegnare a loro un certo insieme di privilegi. Viene anche diagrammato come il consumatore possa iscriversi ad un programma fedeltà e riscuotere i benefici associati ad essa al momento di un acquisto, online o in un negozio fisico. Si spiegano anche le meccaniche della registrazione alla piattaforma da parte di ogni tipologia di utente.



### Implementazione

Sono stati implementati gli use case selezionati al passo 6, cioè, un sottoinsieme di quelli diagrammati.
Il progetto è stato realizzato in java, utilizzando nello specifico il database mysql e il framework spring boot per gestire la persistenza.
È stato realizzato un web service che espone una rest API, testabile con postman.
Il progetto utilizza il gradle build system.





