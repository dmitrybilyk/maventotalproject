* DOCUMENTATION

** INSTALLATION
Anforderungen:
- Unix-like Umgebung (Linux, OSX etc.)
- Python Version 2.5.x
- pylint Kommando ab Version 0.19.0
- php Kommando ab Version 5.2.x
- hg Kommando ab Version 1.2
- pear (für phpcs)
- tidy (für XML-Dateien)
- PHP CodeSniffer ab Version 1.2.0
- optional: Benutzer mit Mercurial Repositories und SSH-Pubkey-Auth

Die Installation ist möglich als:
- gemeinsamer Mercurial hook
- repobezogener Mercurial hook
- standalone für lokale Tests

Bei der Installation als hook hat man im Normalfall einen Benutzer, der die Mercurial Repositories führt. Dort befindet sich in der Regel auch eine ~/.hgrc Datei. Hier muss man folgendes Eintragen damit das QG als hook ausgeführt werden kann:

[hooks]
pretxnchangegroup.qgate = ~/hg/symmetrics_saasrepo_qualitygate/build/main.py

Bei der Installation als repobezogenem hook fügt man die Zeile in die .hg/hgrc
des gewünschten Repositories ein.

Das Script main.py prüft dann bei einem push den Code und gibt im Fehlerfalle eine Begründung zurück.

Abschliessend sollte der forwarder als Cronjob eingetragen werden:
*/5 * * * * cd ~/hg/symmetrics_saasrepo_qualitygate/build/forwarder && ./forwarder.py

Bei der standalone-Variante setzt man die ENV-Variable SYMMETRICS_QUALITYGATE_PROJECT_ROOT auf den build-Ordner dieses Projektes (z.B. ~/hg/symmetrics_saasrepo_qualitygate/build), geht in das zu überprüfende Repository-root und führt einfach die qg.sh (aus ~/hg/symmetrics_saasrepo_qualitygate/build/stuff) aus. Daraufhin wird die Logdatei gefüllt. Hierbei wird auch gleich immer die geloggte Meldung ausgegeben. Der forwarder sollte in dieser Konstellation händisch ausgeführt werden.

Wichtig dabei ist, dass die Pfade in den Einstellungsdateien und die Zugangsdatem zur API der Statistik-UI korrekt sind.

** USAGE
Das Quality Gate besteht aus zwei Teilen. Einer Prüfeinheit und einem forwarder. Die Prüfeinheit kann selbstständig und auch als Mercurial hook verwendet werden. Der forwarder liest das Logfile und leitet die Daten an die Statistik-UI weiter.

An Testzwecken werden hier nur Testcases für die standalone Variante aufgeführt. Die Installation als hook verhält sich ähnlich (Logmeldung wird nicht ausgeben, sondern nur in die Logdatei geschrieben).

Um Code von anderen Projekten in Problemfällen vom Testen auszuschliessen ist es möglich, die Dateien mit einem bestimmten header zu versehen. Das Script stuff/qg_exclude.py [Pfad] übernimmt dies für alle Python und PHP Dateien. Ordner werden rekursiv abgearbeitet.
Um ganze Ordner auszuschliessen, muss in einem Ordner die leere Datei .codingStandardsIgnorePath hinterlegt werden. Daraufhin wird der ganze Ast ignoriert. Dieser Workaround wird jedoch bei jedem Prüfdurchgang protokolliert.

Die Prüfeinheit enthält ausserdem einen bad content Filter, der via Regex-Muster die Inhalte von Code-Dateien prüft. Passt ein Muster (z.B. "[short description, max 60 chars]", "[firstname] [lastname]" etc.), wird sofort abgebrochen. Diese Muster befinden sich im Ordner content_filters. Eine Datei hat als Dateinamen den Namen des Filters gefolgt mit dem Postfix .txt und enthält lediglich ein Regexp-Muster für den Test. Alle anderen Dateinamen und Ordner werden ignoriert. Es ist möglich die Namen mit einer vorangestellten drei- oder vierstelligem Zahl zu versehen um eine entsprechende Reihenfolge bei der Abarbeitung zu erzwingen. Um die Wartung dieser Muster zu vereinfachen bietet es sich an, diese z.B. via FTP zugänglich zu machen. Man kann diesen Ordner auch ausserhalb des Pakets platzieren - dazu muss anschliessend noch die entsprechende Konfigurationsvariable angepasst werden. Hier sollte man jedoch Vorsicht walten lassen, da ein falsches oder defektes Muster eine Ablehnung des zu prüfenden Codes zur Folge hat.

** FUNCTIONALITY
*** A: meta_info_py - Prüft ob die info.py existiert
*** B: meta_pylint - Prüft die info.py mittels pylint auf Fehler
*** C: meta_pep8 - Prüft die info.py mittels pep8.py auf Fehler
*** D: meta_utf8 - Prüft, ob die info.py kompatibel zu UTF-8 ist
*** E: meta_bad_content - Prüft die info.py auf unerlaubte Inhalte
*** F: meta_import - Prüft, ob die info.py geladen werden kann
*** G: meta_fields - Prüft, ob alle Pflichtfelder korrekt ausgefüllt wurden, alle ausgefüllten optionalen Felder korrekt ausgefüllt wurden, mindestens 3 Tags vergeben wurden und das Format des Feldes DEPENDS_ON_FILES korrekt ist.
*** H: meta_dependencies - Prüft, ob alle Abhängigkeiten erfüllbar sind
*** I: package_build - Prüft, ob ein build Ordner, eine build.tar.bz2 oder eine build.tar.gz vorhanden ist
*** J: package_readmes - Prüft, ob der doc-Ordner und darin README-Dateien vorhanden und nicht leer sind
*** K: package_mandatory_readmes - Prüft, ob die verbindlichen README-Dateien vorhanden sind
*** L: package_readmes_parseable - Prüft, ob all README-Dateien geparsed werden können (UTF-8, Syntax, Struktur)
*** M: package_utf8 - Prüft, ob alle Python- und PHP-Dateien kompatibel zu UTF-8 sind
*** N: package_bad_content - Prüft alle Python- und PHP-Dateien anhand von Regex-Mustern auf unerlaubte Inhalte
*** O: package_pylint - Prüft alle Python-Dateien mittels pylint auf Fehler
*** P: package_pep8 - Prüft alle Python-Dateien mittels pep8.py auf Fehler
*** Q: package_phplint - Prüft alle PHP-Dateien mittels php -l auf Fehler
*** R: package_phpcodesniffer - Prüft alle PHP-Dateien mittels dem PHPCodeSniffer auf Fehler
*** S: package_djangolint - Prüft, sofern ein Django-Projekt erkannt werden kann, alle Python-Dateien auf Fehler und die richtige Struktur
*** T: Datei .codingStandardsIgnorePath - Falls ein Ordner so eine Datei enthält wird dieser nicht überprüft (nur für Unterordner im build Ordner möglich) und ein Hinweis darüber protokolliert
*** U: package_xml_valid - Prüft alle XML-Dateien mittels tidy auf Fehler
*** V: Datei .qgignore - Falls der Basisordner so eine Datei enthält werden alle darin genannten Dateien und Pfade (Syntax: glob) nicht überprüft (nur für Unterordner im build Ordner möglich) und ein Hinweis darüber protokolliert

** TECHNICAL
Das Quality Gate besteht derzeit aus einem Prüfskript mit diversen Tests (siehe Abschnitt FUNCTIONALITY) und einem Forwarder, der das Logfile mit allen Meldungen liest und an z.B. MCC weiterreicht.
Das Prüfskript (main.py) besteht aus einer main-Routine und vielen kleinen Funktionen, die die einzelnen Tests darstellen. Schlägt ein Test fehl, wird eine Exception generiert. Dies hat zur Folge, dass keine weiteren Tests durchgeführt werden und gleich zur main-Routine zurückgesprungen wird. Zum Schluss wird eine entsprechende Erfolgs- oder Fehlermeldung im Logfile (config.LOG_FILE) niedergeschrieben.
Der forwarder (forwarder/forwarder.py) liesst periodisch (via cronjob) das Logfile und überträgt neue Einträge an einen Webservice (config.REST_POST_URL). Zum Lesen des Logs liegt ihm ein Apache-Log-Reader bei, der für das derzeitige Logformat dynamisch angepasst wurde. Eine kleine Hilfsdatei (config.LOG_POS_FILE) hält die aktuelle Position gelesenen Logfile fest.

** PROBLEMS
- Doc-Blocks werden noch unzureichend geprüft
- django-lint ist noch deaktiviert, da zu wenig getestet

* TESTCASES

** BASIC
*** A: 1. Leeren Ordner erstellen
       2. qg.sh sollte die info.py vermissen
*** B: 1. Leeren Ordner erstellen
       2. info.py anlegen, ausfüllen und Syntax-Fehler einbauen
       3. qg.sh sollte die info.py ablehnen
*** C: 1. Leeren Ordner erstellen
       2. info.py anlegen, ausfüllen und z.B. zu lange Zeilen einbauen
       3. qg.sh sollte die info.py ablehnen
*** D: 1. Leeren Ordner erstellen
       2. info.py anlegen, ausfüllen und Sonderzeichen im Windows-Zeichensatz oder MacRoman einbauen
       3. qg.sh sollte die info.py ablehnen
*** E: 1. Leeren Ordner erstellen
       2. info.py anlegen und mit Inhalt vom Template belassen
       3. qs.sh sollte die info.py so lange ablehnen, bis alle aktiven Inhalte bearbeitet wurden (alle Filter greifen nicht mehr)
*** F: 1. Leeren Ordner erstellen
       2. info.py anlegen, ausfüllen und keine Fehler einbauen
       3. qg.sh sollte die info.py nicht ablehnen
*** G: 1. Leeren Ordner erstellen
       2. info.py anlegen, ausfüllen und z.B. das Feld INFO leer lassen
       3. qg.sh sollte die info.py ablehnen
*** H: 1. Leeren Ordner erstellen
       2. info.py anlegen, ausfüllen und die Abhängigkeit "GibtEsNicht" eintragen
       3. qg.sh sollte die Abhängigkeit ablehnen
*** I: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. qg.sh sollte den build Ordner und die Archive vermissen
*** J: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. qg.sh sollte die README-Dateien vermissen
*** K: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-EN.txt anlegen und mit einem Leerzeichen füllen
       5. qg.sh sollte die README-DE.txt vermissen
*** L: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem Leerzeichen füllen
       5. qg.sh sollte die README-DE.txt ablehnen
       6. doc/README-DE.txt mit einem ausreichenden Inhalt füllen
       7. qg.sh sollte die README-DE.txt nicht ablehnen
*** M: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.py mit einem Kommentar und Sonderzeichen im Windows-Zeichensatz oder MacRoman füllen
       6. build/test.php mit einem Kommentar und Sonderzeichen im Windows-Zeichensatz oder MacRoman füllen
       7. qg.sh sollte die build/test.py ablehnen
       8. build/test.py löschen
       9. qg.sh sollte die build/test.php ablehnen
       10. build/test.php löschen
       11. qg.sh sollte nun nichts mehr ablehnen
*** N: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.py mit einer Variable und dem Inhalt "[firstname] [lastname]" füllen
       6. qg.sh sollte die build/test.py ablehnen
*** O: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.py mit etwas Code und einem Syntaxfehler füllen
       6. qg.sh sollte die build/test.py ablehnen
*** P: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.py mit einem 120 Zeichen langem Kommentar füllen
       6. qg.sh sollte die build/test.py ablehnen
*** Q: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.php mit etwas Code und einem Syntaxfehler füllen
       6. qg.sh sollte die build/test.php ablehnen
*** R: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.php mit etwas Code füllen und z.B. am Anfang der Datei ein Kommentar mit einem Hash einfügen
       6. qg.sh sollte die build/test.php ablehnen
*** S: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. Ein Django-Projekt mit dem Namen vom aktuellen Ordner anlegen
       6. Den Ordner mit dem Django-Projekt in build umbenennen
       7. qg_exclude.py auf build/manage.py anwenden
       8. qg_exclude.py auf build/settings.py anwenden
       9. qg.sh sollte den Code annehmen
       10. In der build/settings.py die Variable MANAGERS auskommentieren
       11. qg.sh sollte die build/settings.py ablehnen
*** T: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.py mit etwas Code und keinem Fehler füllen
       6. build/submodule Ordner anlegen
       7. build/submodule/error.py mit etwas Code und einem Syntaxfehler füllen
       8. qg.sh sollte die build/submodule/error.py ablehnen
       9. Datei build/submodule/.codingStandardsIgnorePath anlegen
       10. qg.sh sollte die build/submodule/error.py ignorieren und den Inhalt annehmen
       11. Im Log sollte ein Hinweis auf den ignorierten Ordner sichtbar sein
*** U: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.xml mit etwas XML und einem Syntaxfehler füllen
       6. qg.sh sollte die build/test.xml ablehnen
*** V: 1. Leeren Ordner erstellen
       2. info.py anlegen und ausfüllen
       3. doc Ordner anlegen
       4. doc/README-DE.txt anlegen und mit einem ausreichenden Inhalt füllen
       5. build/test.py mit etwas Code und keinem Fehler füllen
       6. build/submodule Ordner anlegen
       7. build/submodule/error.py mit etwas Code und einem Syntaxfehler füllen
       8. qg.sh sollte die build/submodule/error.py ablehnen
       9. Datei .qgignore anlegen und mit "build/submodule/error.py" füllen
       10. qg.sh sollte die build/submodule/error.py ignorieren und den Inhalt annehmen
       11. Im Log sollte ein Hinweis auf die ignorierte Datei sichtbar sein
       12. Datei .qgignore mit "build/submodule*" erweitern
       13. qg.sh sollte die build/submodule ignorieren und den Inhalt annehmen
       14. Im Log sollte ein Hinweis auf den ignorierten Ordner sichtbar sein
