import org.scalatest.{BeforeAndAfterAll, FunSuite}
import scalikejdbc.{SQL, DB}
import scalikejdbc.config.DBs

class PriceIT extends FunSuite with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = {
    println("database created: " + RedshiftClientJDBC.createDBWithUniqueName)

  }

  test("overall price calculation") {

    RedshiftClientJDBC.executeCreateTableDDL("purchases",scala.io.Source.fromFile (("src/main/sql/ddl/create_purchases.sql")).mkString)
    RedshiftClientJDBC.executeCreateTableDDL("customers",scala.io.Source.fromFile (("src/main/sql/ddl/create_customers.sql")).mkString)

    val select_stmt=scala.io.Source.fromFile (("src/main/sql/analysis/overall_price_for_customerclass.sql")).mkString

    DBs.setupAll()
    DB readOnly { implicit session =>
      val result = SQL(select_stmt).map(rs => rs.string("price")).single().apply()
      println(result)
      assert (result === 18.5)
    }


  }

  override protected def afterAll(): Unit = {
     RedshiftClientJDBC.dropDatabase()
  }
}
