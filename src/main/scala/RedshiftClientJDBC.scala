

import org.joda.time.DateTime
import scalikejdbc.config._
import scalikejdbc.{DB, _}

object RedshiftClientJDBC {


  var dbName:String  =""



  /** For test isolation this method creates a database where one run of integration tests  will be executed.
    *
    * @return Name of the created database
   */
  def createDBWithUniqueName (): String = {
    val millis: Long = System.currentTimeMillis / 1000
    def username = System.getenv("USER")
    val dbName = s"it_${username}_${millis}"
    val ddl = s"create database ${dbName}"

    DBs.setupAll()
    DB autoCommit  {implicit session =>
      SQL(ddl).execute().apply()
    }
    this.dbName=dbName

    return dbName
  }

  def dropDatabase (): Unit = {
    val ddl = s"drop database ${this.dbName}"

    DBs.setupAll()
    DB autoCommit  {implicit session =>
      SQL(ddl).execute().apply()
    }
    this.dbName=null
  }


  def executeDDL(ddl: String)= {
    DBs.setupAll()

    DB autoCommit  {implicit session =>
      SQL(ddl).execute().apply()
    }
  }

  def executeCreateTableDDL(tableName: String, ddl: String)= {
    DBs.setupAll()
    DB autoCommit { implicit session =>
      if (DB.getTable(tableName) != None ) {
        println(s"table ${tableName} exists. Drop it now.")
        SQL(s"drop table ${tableName}").execute.apply()
      }
      SQL(ddl).execute().apply()
    }
  }




  def getQueryResult(select: String) ={
    DBs.setupAll()

    DB readOnly  {implicit session =>
      SQL(select).execute().apply()
    }
  }

  def getResult(): Unit = {

    /*
      register connection pool for all db configurations in application.conf
     */
    DBs.setupAll()
    val ids = Seq(1, 2, 3)

    implicit val session: DBSession = DB.autoCommitSession()

    case class Emp(id: BigInt, name: String)

    val id = 1
    val name = "mirko"

    sql"create table mpr_blob_example (id bigint, name varchar(200), created_at TIMESTAMP )".execute().apply()

    DB.getAllTableNames.filter(_.toLowerCase == "mpr_blob_example").foreach(println)

    sql"""insert into mpr_blob_example (id, name, created_at) values (${id}, ${name}, ${DateTime.now})"""
      .update.apply()


    val created_at =
      sql"select created_at from mpr_blob_example where id = ${id}".map(_.string("created_at")).single.apply()

    println(s"the created_at is ${created_at}")



    DB readOnly { implicit session =>
      sql"select count(*) from mpr_blob_example where id in ${ids}"
    }


    val anzahl: Option[String] = DB readOnly { implicit session =>
      sql"select count(*) as anz from information_schema.tables".map(rs => rs.string("anz")).single.apply()
    }
    println(anzahl)


    /*
      release all connection pools
     */

    DBs.closeAll()
  }
}

