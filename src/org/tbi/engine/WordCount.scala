package org.tbi.engine

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 * Word Count with Spark.
 * -- Count the words in "README.md" (ZenOfPython.txt).
 * @author Jason
 */
object WordCount {
  def main(args: Array[String]) {
    // Create Spark Context:
    val conf = new SparkConf().setMaster("local").setAppName("wordCount").setIfMissing("spark.dynamicAllocation.minExecutors", "1")
    val sc = new SparkContext(conf)

    // Load our input data:
    val inputFile = "ZenOfPython.txt"
    val input = sc.textFile(inputFile)
    println("Total number of lines in this file: " + input.count())
    println("First 10 rows:")
    input.take(10).foreach(println)

    // Split it up into words:   
    val words = input.flatMap(line => line.split(" "))

    // Transform into pairs and count:
    val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y } // Save the word count back out to a text file, causing evaluation. counts.saveAsTextFile(outputFile)

    // Save results into a file:
    val outputFile = "output.txt"
    counts.saveAsTextFile(outputFile)

    // Shut down Spark:
    sc.stop()
  }
}