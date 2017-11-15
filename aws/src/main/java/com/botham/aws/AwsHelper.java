package com.botham.aws;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndexDescription;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/*
AWS credentials provider chain that looks for credentials in this order: 
Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY (RECOMMENDED since they are recognized by all the AWS SDKs and CLI except for .NET), or AWS_ACCESS_KEY and AWS_SECRET_KEY (only recognized by Java SDK)
 
Java System Properties - 
aws.accessKeyId and aws.secretKey using this from run config..Arguments/VM Arguments

-Daws.accessKeyId=AKIAIMLGC5LUD3CFZTNA -Daws.secretKey=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

Credential profiles file at the default location (~/.aws/credentials) shared by all AWS SDKs and the AWS CLI
C:\Users\steve\.aws

this looks for [default] profile only for some reason..



Credentials delivered through the Amazon EC2 container service if AWS_CONTAINER_CREDENTIALS_RELATIVE_URI" environment variable is set and security manager has permission to access the variable,
Instance profile credentials delivered through the Amazon EC2 metadata service
*/

public class AwsHelper {
	
	public static String region_us_east_1 = "us-east-1";
	public static String region_us_east_2 = "us-east-2";
	public static String region_us_west_2 = "us-west-2";
	
	public static String credProfile="serviceacc"; // <- Relates to C:\Users\steve\.aws\credentials
	
	public static Logger log = LoggerFactory.getLogger(AwsHelper.class);
	
	public static void s3ListBuckets(String region) {
		
		String mName = "s3";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}

		AmazonS3 s3 = AmazonS3Client.builder().withCredentials(new ProfileCredentialsProvider(credProfile)).
				                               withRegion(region).
				                               withPathStyleAccessEnabled(true).build();
		
		

		List<Bucket> buckets = s3.listBuckets();
		System.out.println("Your Amazon S3 buckets are:");
		for (Bucket b : buckets) {
			System.out.println("* name=" + b.getName()+" owner="+b.getOwner().getId());

		}

		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}
	
	
	public static void s3AddToBucket(String bucket, String key, File file, String region) {
        String mName="s3AddToBucket";
        if (log.isDebugEnabled()) {
        	log.debug(mName+" Starts, bucket="+bucket+" key="+key+" file="+file.getAbsolutePath());
        }
		
	    //File file = new File(localFilePath);
	    
		if (!file.exists()) {
			log.error(" "+file.getAbsolutePath()+" Does not exist");
			System.exit(1);
		}
		
		//String keyName = Paths.get(filePath).getFileName().toString();
// Designates remote path... Does not need to exist..
		//String keyName = "pubs/321/20170602/p.pdf";
		
		AmazonS3 s3 = AmazonS3Client.builder().withCredentials(new ProfileCredentialsProvider(credProfile)).
                withRegion(region).
                withPathStyleAccessEnabled(true).build();
		try {
			
			s3.putObject(bucket, key, file);
			
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
	}
	
	
	
	public static void snsTextToPhone(String phoneNumber, String message, String region) {

		AmazonSNS sns = AmazonSNSClient.builder().withCredentials(new ProfileCredentialsProvider(credProfile)).
				                                  withRegion(region).build();

		//String message = "My SMS message";
		//String phoneNumber = "+18133752493";
		
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
		// <set SMS attributes>
		sendSMSMessage(sns, message, phoneNumber, smsAttributes);
	}
	
	public static void sendSMSMessage(AmazonSNS snsClient, String message, String phoneNumber,
			Map<String, MessageAttributeValue> smsAttributes) {
		PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));
		System.out.println(result); // Prints the message ID.
	}
	
	
	
	public static void getFromDBByKey(String region) {

		AmazonDynamoDB client  = AmazonDynamoDBClient.builder().withCredentials(new ProfileCredentialsProvider(credProfile)).
				                                  withRegion(region).build();

// Build the key		
		        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		        String id="1";
		        key.put("id", new AttributeValue().withS(id));

// Go get the data
		        GetItemRequest request = new GetItemRequest()
		            .withTableName("Music")
		            .withKey(key);

		        try {
		            GetItemResult result = client.getItem(request);
		            if (result != null && result.getItem() != null) {
		                AttributeValue name = result.getItem().get("name");
		                System.out.println("The name for "+id+" is " + name);
		            } else {
		                System.out.println("No row was found");
		            }
		        } catch (Exception e) {
		            System.err.println("Unable to retrieve data: ");
		            System.err.println(e.getMessage());
		        }
		    }
	
	
	public static void putItem(String region) {
		
		AmazonDynamoDB client  = AmazonDynamoDBClient.builder().withCredentials(new ProfileCredentialsProvider(credProfile)).
                withRegion(region).build();
		
		DynamoDB dynamoDB = new DynamoDB(client);
		
		Table table = dynamoDB.getTable("Music");
		
		Item item = new Item()
			    .withPrimaryKey("id", "4")
			    .withString("name", "Billy Bob Thorton");
		
		PutItemOutcome outcome = table.putItem(item);
		
		 item = new Item()
			    .withPrimaryKey("id", "5")
			    .withString("name", "Billy Smith");
		
		 outcome = table.putItem(item);
		 
		 System.out.println(outcome.toString());
		
	}
	
	public static void getAllWithScan(String region, Integer startPage, Integer pageSize) {

		AmazonDynamoDB client = AmazonDynamoDBClient.builder()
				.withCredentials(new ProfileCredentialsProvider(credProfile)).withRegion(region).build();

		ScanRequest scanRequest = new ScanRequest().withTableName("Music").withLimit(pageSize);

		ScanResult result = client.scan(scanRequest);
		System.out.println("returned items=" + result.getItems().size());
		for (Map<String, AttributeValue> item : result.getItems()) {
			System.out.println(item.toString());
		}
	}
	
	
	
	// https://tech.smartling.com/getting-started-with-amazon-dynamodb-and-java-universal-language-850fa1c8a902
	
// When you query the GSI with partition key attribute, only equality operator is allowed for the partition key. 
//	If you don't know the value of partition key, you need to use scan api.	
	public static void getQuery(String region) {

		AmazonDynamoDB client = AmazonDynamoDBClient.builder()
				.withCredentials(new ProfileCredentialsProvider(credProfile)).withRegion(region).build();
//
		
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable("Music");
		
		
		
//		String nameExpr = "contains(name, :name";
//		ValueMap v = new ValueMap().withString(":name", "K");
//		
		Map<String, AttributeValue> eav = new HashMap<>();
		eav.put(":myname", new AttributeValue().withS("K"));
		

		QuerySpec spec1 = new QuerySpec().withKeyConditionExpression("id > :v_id").
				withValueMap(new ValueMap().withString(":v_id", "0"));
		
		QuerySpec spec2 = new QuerySpec().withKeyConditionExpression("userName = :v_userName").
				withValueMap(new ValueMap().withString(":v_userName", "Steve Botham"));
		
		QuerySpec spec3 = new QuerySpec().withKeyConditionExpression(new String());

		
		ItemCollection<QueryOutcome> items = table.query(spec1);
		Iterator<Item> iterator = items.iterator();
		Item item = null;
		while (iterator.hasNext()) {
		    item = iterator.next();
		    System.out.println(item.toString());
		}
		
		
//		QueryRequest request = new QueryRequest().withKeyConditionExpression("id = :v_id").
//				withValueMap(new ValueMap().withString(":v_id", "1")).
//				withTableName("Music").
//				withFilterExpression("contains(userName, :myname)").withExpressionAttributeValues(eav);
//				
//		

//		QueryResult result = client.query(request);
//		System.out.println("returned items=" + result.getItems().size());
//		for (Map<String, AttributeValue> item : result.getItems()) {
//			System.out.println(item.toString());
//		}
	}
	
	
	public static void getByIndex(String region) {
		String mName="getByIndex";
		AmazonDynamoDB client = AmazonDynamoDBClient.builder()
				.withCredentials(new ProfileCredentialsProvider(credProfile)).withRegion(region).build();
//
		
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable("Music");
		Index index = table.getIndex("superUser-index");
		
		TableDescription tableDesc = table.describe();
		Iterator<GlobalSecondaryIndexDescription> gsiIter = tableDesc.getGlobalSecondaryIndexes().iterator();
		while (gsiIter.hasNext()) {
		    GlobalSecondaryIndexDescription gsiDesc = gsiIter.next();
		    System.out.println("Info for index "
		         + gsiDesc.getIndexName() + ":");
		}
		
		
		QuerySpec spec = new QuerySpec();

		spec.withKeyConditionExpression("superUser = :val")
		    .withValueMap(new ValueMap().withString(":val", "Y"));
		  
		//ItemCollection<QueryOutcome> customers = client.getTable("Music").getIndex("premiumIndex").query(spec);
		ItemCollection<QueryOutcome> matches = index.query(spec);
		
		log.debug(matches.toString());
		log.debug("items="+matches.firstPage());
		
		Iterator<Item> i = matches.iterator();
		while (i.hasNext()) {
		
			Item item = i.next();
			log.debug(mName+" "+item.toString());
		}
	}
	
	
	public static void sqsReceive(String region) {
		String mName = "sqsReceive";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}

		AmazonSQS sqs = AmazonSQSClient.builder().withRegion(region).build();

		String queueUrl = "https://sqs.us-east-1.amazonaws.com/964321415537/saleln.fifo";

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withMaxNumberOfMessages(10)
				.withQueueUrl(queueUrl);

		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

		if (log.isDebugEnabled()) {
			log.debug(mName + " items in queue=" + messages.size());
		}

		boolean deleteMessages = true;
		deleteMessages = false;

		int x = 0;
		for (Message m : messages) {
			x++;
			//log.debug(mName + " x=" + x + " message=" + m.toString());
			log.debug(mName+" "+m.getBody());
			if (deleteMessages) {
				sqs.deleteMessage(queueUrl, m.getReceiptHandle());
			}
		}

		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}

	public static void sqsSend(String region) {
		String mName = "sqsSend";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}

		AmazonSQS sqs = AmazonSQSClient.builder().withRegion(region).build();

		String queueUrl = "https://sqs.us-east-1.amazonaws.com/964321415537/saleln.fifo";
		// sqs.
		// List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();

		SendMessageRequest s = new SendMessageRequest().withMessageBody("hello world")
				.withMessageDeduplicationId(String.valueOf(System.currentTimeMillis())).withMessageGroupId("1")
				.withQueueUrl(queueUrl);
		
		

		sqs.sendMessage(s);

		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}


	
	
}
