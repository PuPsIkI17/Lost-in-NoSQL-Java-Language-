# Projects
Lost in NoSQL(Java Language)
Implementing a NoSQL database, distributed on multiple nodes called LNoSQL, which supports a simple query language.

Pislari Vadim -> Facultatea Automatica si Calculatoare -> Universitatea Politehnica Bucuresti

                                                 (Romanian language)
                                               -------DESCRIERE-------
Scopul acestui proiect este de a simula implementarea unei baze de date NoSQL, distribuită pe mai multe noduri numita LNoSQL, ce suportă un limbaj simplu de interogare.
 
Bazele de date relaționale sunt folosite în implementarea multor aplicații web, bancare și reprezintă baza multor implementări ale modului de stocare a datelor. Putem alege intre doua implementări generice in funcție de modelarea datelor: baze de date relaționale ce folosesc limbajul MySQL sau baze de date non-relaționale ce pot avea diverse implementări (document, key sau column). 

 Baza de date non-relațională numită “Lost in NoSQL” este folosită pentru a stoca eficient date sub formă de entități ce vor fi repartizate pe mai multe noduri N. Vor putea fi stocate mai multe tipuri de entități caracterizate de atribute, dintre care unul va fi considerat cheie primară (după care va fi identificată unic o valoare a entității stocate în tabel).  
 
 Baza de date LNoSQL trebuie sa asigure si redundanta datelor, ceea ce înseamnă ca datele vor putea fi disponibile indiferent de cate noduri sunt up la un moment dat. 
 
 În acest sens sunt implementate următoarele funcționalități:  
</br>-Fiecare entitate are o caracteristica ce poate fi configurată la creare numita factor de replicare (RF), ce va fi mai mica sau egala cu numărul de noduri N (RF <= N).  Acest factor care reprezintă numărul de copii ale instanțelor entităților pe fiecare dintre nodurile bazei de date (de exemplu, daca avem o baza de date cu N = 5 noduri si entitatea Produs are RF = 3 atunci orice intrare a entității Produs va trebui scrisa in 3 din cele 5 noduri ale bazei de date).  
</br>- Un nod al bazei de date are capacitate limitata (MaxCapacity) si daca se atinge aceasta limita scrierea se face în următorul nod care este liber (nu și-a atins capacitatea maximă). 
</br>- Un nod al bazei de date poate sa conțină entități de toate tipurile declarate și nu este constrâns în a stoca un singur tip de entitate. 
</br>- Fiecare entitate din baza de date este caracterizata de mai multe atribute ce pot fi de tipul String, Integer sau Float. 
</br>- Ordinea de scriere în nodurile bazei de date se face începând cu cel mai ocupat nod. Daca doua noduri au același grad de ocupare se alege cel cu id-ul mai mic. 
</br>- Fiecare entitate are o cheie primara după care se identifica unic fiecare instanță și este întotdeauna primul atribut al entității la creare. 
</br>- Pentru fiecare instanță a unei entități din baza de date se ține cont de data la care s-a făcut ultimul update (se foloseste pentru aceasta timestamp-ul local al mașinii).   
  
  Limbajul de Interogare – LQL 
</br>Ca orice baza de date, fie SQL sau NoSQL, suporta un set de comenzi pentru crearea entităților, inserarea de instanțe ale entităților, actualizare si ștergere. Pentru baza de date LNoSQL acestea sunt:  
</br>i) Creare Baza de date:
</br>- Comanda prin care se creează baza de date cu constrângerile menționate de număr de noduri si capacitate maxima a unui nod. 
</br>- Comanda: CREATEDB "Db_Name" "No_Nodes" "Max_Capacity" 
</br>ii) Creare Entitate: 
</br>- Comanda prin care se creează o entitate cu un factor de replicare si atributele sale care pot fi de tipul: String, Integer sau Float. Ordinea atributelor din comanda de creare se reflecta si in celelalte comenzi.
</br>- Primul atribut este considerat cheie primara – după care o instanță a unei entități este identificata unic. 
</br>- Comanda: CREATE "Entity" "RF" "No_Attributes" Atribute_1 Tip_Atribut1  Atribute_2 Tip_Atribut2 …. Atribute_n Tip_Atributn 
</br>iii) Inserare instanță: 
</br>- Comanda prin care se inserează o instanță a unei entități. Operația înseamnă replicarea instanței pe nodurile bazei de date in funcție de RF specific entității.  
</br>- Inserarea într-un nod se face ordonat după cea mai recentă intrare. 
</br>- Comanda: INSERT "Entity" "Val_Attr1" "Val_Attr2" ... "Val_Attrn" 
</br>iv) Ștergere instanță:
</br>- Se vor șterge toate copiile instanței respective de pe toate nodurile în care a fost inserată 
</br>- Val_attr1 reprezinta valoarea cheii primare pentru acea instanță 
</br>- În cazul în care nu exista se printeaza un mesaj de eroare: „NO INSTANCE TO DELETE” 
</br>- Comanda: DELETE "Entity" "Primary_Key" 
</br>v) Actualizare instanță: 
</br>- Actualizează toate copiile instanței respective de pe toate nodurile în care a fost inserata 
</br>- Comanda: UPDATE "Entity" "Primary_Key" "Attr1" "New_Val_Attr1" ... "Attrn" "New_Val_Attrn"  
</br>vi) Regăsire instanță  
</br>- Returnează o instanță cu toate valorile și nodurile în care se află. 
</br>- Daca nu exista acea instanță se returneaza un mesaj de eroare „NO INSTANCE FOUND” 
</br>- Formatul pentru printarea valorile de tip float foloseste DecimalFormat cu formatul #.## 
</br>- Comanda: GET "Entity" "Primary_Key" 
</br>- Rezultat: "Node_1" "Node_2"..."Node_n" "Entity" "Attr1":"Val_Attr1" ... "Attrn":"Val_Attrn"  
</br>vii) Print baza de date 
</br>- Printează întreaga baza de date împreuna cu toate nodurile si datele ce se regăsesc la acel moment în fiecare nod. 
</br>- În cazul în care baza de date nu conține nicio instanță a unei entități se va afișa mesajul de eroare „EMPTY DB”  
</br>- Comanda: SNAPSHOTDB 
</br>- Rezultat: "Nod_1" "ENTITY1" "Attr1":"Val_Attr1" ... "Attrn":"Val_Attrn"  "ENTITY2" "Attr1: "Val_Attr1" ... "Attrn":"Val_Attrn"  ... "Nod_2" "ENTITY1" "Attr1":"Val_Attr1" ... "Attrn":"Val_Attrn"  ... 
</br>i) Clean-up  
</br>- Comanda are ca efect ștergerea instanțelor mai vechi de un anumit timestamp (nanosecunde) dat ca parametru. 
</br>- De asemenea, se pastreaza în continuare ordinea de scriere în noduri după cele mai recente intrări. 
</br>- Comanda: CLEANUP "DB_NAME" "TIMESTAMP(ns)" 
</br>ii) Full Database 
</br>- Se scaleaza baza de date in cazul in care este umpluta la capacitate maxima (nu mai este loc de inserare in niciun nod)  
</br>- In acest caz, se creaza un nou nod de fiecare data cu aceeași capacitate <Max_Capacitaty> ca a celorlalte noduri. 
