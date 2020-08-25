package com.example.dynamodbdemo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

public class DynamoDbHandler {

	public static void main(String[] args) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
				new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-south-1"))
				.build();
		
		DynamoDB dynamoDB = new DynamoDB(client);
		TableDescription tableDescription = 
			    dynamoDB.getTable("Music").describe();
		
		System.out.printf("%s: %s \t ReadCapacityUnits: %d \t WriteCapacityUnits: %d",
			    tableDescription.getTableStatus(),
			    tableDescription.getTableName(),
			    tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
			    tableDescription.getProvisionedThroughput().getWriteCapacityUnits()); 
	}

}
