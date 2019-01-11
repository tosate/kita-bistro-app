# Kita Bistro App

Die Kita Bistro App bietet Essenslisten, in denen eingetragen werden kann welches Kind bereits gegessen hat bzw. welches Kind noch nicht gegessen hat. Die Applikation unterstützt Essenslisten für Frühstück und Mittagessen. Für jedes Kind kann hinterlegt werden, ob es überhaupt in der Kita frühstückt oder zu Mittag isst. Zudem können besondere Eigenschaften gepflegt werden (z.B. Allergien).

## Architektur
Die Software ist eine Java Maven Web-Anwendung. Als Web Technologie wird JSP verwendet. Die Daten werden in einer MariaDB gespeichert.
Benötigte Javascript module werden mit Hilfe von [webpack][9071c784] verwaltet. Dazu zählt unter anderem [Bootstrap][c8e38973], das CSS templates bereitstellt.

  [9071c784]: https://webpack.js.org/ "webpack"
  [c8e38973]: https://getbootstrap.com/ "Bootstrap"
