import com.mongodb.casbah.Imports._

/**
 * @author Jason
 */
object MongoTest extends App {

  connectToMongo

  def connectToMongo {
    /*
    val mongoConn = MongoConnection()
    val mongoColl = mongoConn("test")("vikas")
    val bread1 = MongoDBObject("name" -> "parrys",
      "price" -> "10 INR")
    val bread2 = MongoDBObject("name" -> "breadAndMore")
    mongoColl += bread1
    mongoColl += bread2
    mongoColl.find()
    

    for { x <- mongoColl } yield println("value " + x)
    */
    
    println("test")
    
    val mongoClient = MongoClient("localhost", 27017)
    val db = mongoClient("test")

    val coll = db("test")

    val a = MongoDBObject("hello" -> "world")
    val b = MongoDBObject("language" -> "scala")

    coll.insert(a)
    coll.insert(b)
    println(coll.count())

    val allDocs = coll.find()
    println(allDocs)
    for (doc <- allDocs) println(doc)

  }
}