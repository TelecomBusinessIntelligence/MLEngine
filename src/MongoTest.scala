import scala.annotation.implicitNotFound

import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject

/**
 * @author Jason
 */
object MongoTest extends App {
  query

  def query {
    val mongoClient = MongoClient("localhost", 27017)
    val db = mongoClient("test")

    val coll = db("test")

    val a = MongoDBObject("hello" -> "world")
    val b = MongoDBObject("lname" -> "Chen", "fname" -> "Jun")
    val c = MongoDBObject("language" -> "scala")
    val d = MongoDBObject("gender" -> "M")
    
    // Cannot insert a document into the same collection with an id that is already in the collection.    
    /*val a = MongoDBObject("_id" -> "1", "hello" -> "world")
    val b = MongoDBObject("_id" -> "2", "lname" -> "Chen", "fname" -> "Jun")
    val c = MongoDBObject("_id" -> "3", "language" -> "scala")
    val d = MongoDBObject("_id" -> "4", "gender" -> "M")*/
    
    coll.insert(a)
    coll.insert(b)
    coll.insert(c)
    coll.insert(d)

    println("Total number of documents in this collection is: " + coll.count())
    val allDocs = coll.find()
    for (doc <- allDocs) println(doc)

    // Find values corresponding to the given key in all documents in the given collection:    
    val cursor = coll.find()
    val key: String = "hello" // specify the query.
    var result: Object = ""
    println("\n\n\n=> search " + key + " :")
    for (doc <- cursor) {
      result = doc.get(key)
      if (result != null) {
        println(result.toString() + ": in document " + doc.get("_id").toString())
      }
    }

    mongoClient.close()
  }

}