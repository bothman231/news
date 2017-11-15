package com.botham.aws;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.AmazonServiceException;

import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManagerBuilder;
import com.amazonaws.services.glacier.transfer.UploadResult;
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
* This code expects that you have AWS credentials set up per:
* http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
*/


public class StartPoint {
	public static String region1 = "us-east-1";

	public static String region2 = "us-west-2";

	public static Logger log = LoggerFactory.getLogger(StartPoint.class);

	public static void main(String[] args) {
		

		
		
		//AwsHelper.s3ListBuckets();
		//AwsHelper.s3AddToBucket("sxb-4813", "1", new File("c:\\pubs\\320\\20170602\\FC\\1.PDF"), AwsHelper.region_us_east_1);
		
		// sns();
		// sqsSend();
		//sqsReceive();
		//AwsHelper.snsTextToPhone("+18133752493", "hello", region1);
		
		
		//AwsHelper.getFromDBByKey(region1);
		//AwsHelper.putItem(region1);
		
		
		//AwsHelper.getAllWithScan(region1, 1, 10);
		
		//AwsHelper.getQuery(region1);
		
		//AwsHelper.sqsSend(region1);
		//AwsHelper.sqsReceive(region1);
		
		AwsHelper.getByIndex(region1);
		
		//glacierUpload();
		log.debug(" Main");
		log.info(" Main");
		
		

	}
	



	
/*
 * You must include that information in the "Key" parameter. S3 isn't actually a filesystem, 
 * it's more like a big (hash table) associative array. The "Bucket" is the name of the hash 
 * table, and the "Key" is the key (e.g., $bucket[$key] = $content). So all path/directory 
 * information must be a part of the "Key".
 * 
 * 	
 */

	
	public static void sns() {
		String mName = "sns";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}

		// create a new SNS client and set endpoint

		AmazonSNS sns = AmazonSNSClient.builder().withRegion(region1).build();

		// This 1 topic can have many subscriptions
		// sbotham1968@gmail.com
		// 231saleln@gmail.com

		String topicArn = "arn:aws:sns:us-east-1:964321415537:231saleln";

		// publish to an SNS topic
		String msg = "My text published to SNS topic with email endpoint";
		PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		PublishResult publishResult = sns.publish(publishRequest);
		// print MessageId of message published to SNS topic
		System.out.println("MessageId - " + publishResult.getMessageId());

		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}

	



	public static void glacierUpload() {
String mName="glacierUpload";

//String vaultArn="arn:aws:glacier:us-east-1:964321415537:vaults/news";

		String archiveToUpload = "\\tmp\\2.pdf";
		File file = new File(archiveToUpload);
		if (file.exists()) {
			log.debug(mName+" Ok to upload");
		} else {
			log.debug(mName+" No file");
			return;
		}
		
		
		AmazonGlacier g = AmazonGlacierClient.builder().withRegion(region1).build();

		String vaultName = "news";

		try {
			ArchiveTransferManagerBuilder b = new ArchiveTransferManagerBuilder().withGlacierClient(g);

			ArchiveTransferManager atm = b.build();

			
			UploadResult result = atm.upload(vaultName, "my archive " + (new Date()), file);
			
	
			
			System.out.println("Archive ID: " + result.getArchiveId());
		

		} catch (Exception e) {
			log.error(mName+" "+e.getCause().getMessage());
			System.err.println(e);
		}
	}
	
	
	
	
}
