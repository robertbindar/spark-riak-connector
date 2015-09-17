package com.basho.spark.connector.perf.dataset

import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.s3.AmazonS3Client
import scala.collection.JavaConversions._


/**
 * @author anekhaev
 */
object S3Client {

  val defaultS3Credentials = new DefaultAWSCredentialsProviderChain()

  private val s3Endpoint = "s3.amazonaws.com"

  private val client = new AmazonS3Client(defaultS3Credentials)
  client.setEndpoint(s3Endpoint)

  def listChildrenKeys(bucket: String, path: String): List[String] = {
    client
      .listObjects(bucket, path)
      .getObjectSummaries.toList
      .map(s => s.getKey)
      .filter(!_.contains("$folder$"))
  }

  //  def loadTextFile(bucket: String, key: String): Iterator[String] = {
  //    val obj = client.getObject(new GetObjectRequest(bucket, key))
  //    Source.fromInputStream(obj.getObjectContent).getLines()
  //  }

}