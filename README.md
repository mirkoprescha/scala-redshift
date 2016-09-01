## prequisites

- download redshift-jdbc-driver (e.g. RedshiftJDBC42-1.1.17.1017.jar) and place it in src/main/resources. Add it to classpath. scalikejdbc-config will automatically load it.

- add an application.conf in src/main/resources
' db.default.driver="com.amazon.redshift.jdbc42.Driver"
  db.default.url="jdbc:redshift://....ssl=true&sslFactory=com.amazon.redshift.ssl.NonValidatingFactory"
  db.default.user=...
  db.default.password=...'




## Open
- case class for entity members








