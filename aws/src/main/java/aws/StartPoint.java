package aws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

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

@SpringBootApplication
@Configuration
public class StartPoint {
	public static String region = "us-east-1";

	public static Logger log = LoggerFactory.getLogger(StartPoint.class);

	public static void main(String[] args) {
		//s3();
		//sns();
		sqsSend();
		sqsReceive();
		//snsTextToPhone();
	}

	public static void s3() {
		String mName = "s3";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}

		AmazonS3 s3 = AmazonS3Client.builder().withRegion(region).withPathStyleAccessEnabled(true).build();

		List<Bucket> buckets = s3.listBuckets();
		System.out.println("Your Amazon S3 buckets are:");
		for (Bucket b : buckets) {
			System.out.println("* " + b.getName());

		}

		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}

	public static void sns() {
		String mName = "sns";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}

		// create a new SNS client and set endpoint

		AmazonSNS sns = AmazonSNSClient.builder().withRegion(region).build();

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
	
	
	public static void sqsReceive() {
		String mName = "sqsReceive";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}


		AmazonSQS sqs = AmazonSQSClient.builder().withRegion(region).build();

		String queueUrl="https://sqs.us-east-1.amazonaws.com/964321415537/saleln.fifo";

	
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
				 .withMaxNumberOfMessages(10)
				 .withQueueUrl(queueUrl);
		
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		
		if (log.isDebugEnabled()) {
			log.debug(mName+" items in queue="+messages.size());
		}
		
		boolean deleteMessages=true;
		deleteMessages=false;
		
		int x=0;
		for (Message m : messages) {
			x++;
			log.debug(mName+" x="+x+" message="+m.toString());
		    if (deleteMessages) {
		    	sqs.deleteMessage(queueUrl, m.getReceiptHandle());
		    }
		}
		
		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}
	
	public static void sqsSend() {
		String mName = "sqsSend";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Starts");
		}


		AmazonSQS sqs = AmazonSQSClient.builder().withRegion(region).build();

		String queueUrl="https://sqs.us-east-1.amazonaws.com/964321415537/saleln.fifo";
		//sqs.
		//List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
	
		SendMessageRequest s = new SendMessageRequest()
				.withMessageBody("hello world")
				.withMessageDeduplicationId(String.valueOf(System.currentTimeMillis()))
				.withMessageGroupId("1")
				 .withQueueUrl(queueUrl);
		
		sqs.sendMessage(s);
		
		
		
		if (log.isDebugEnabled()) {
			log.debug(mName + " Ends");
		}
	}
	
	
public static void snsTextToPhone() {
	
	AmazonSNS sns = AmazonSNSClient.builder().withRegion(region).build();


	    String message = "My SMS message";
	    String phoneNumber = "+18133752493";
	    Map<String, MessageAttributeValue> smsAttributes = 
	            new HashMap<String, MessageAttributeValue>();
	    //<set SMS attributes>
	    sendSMSMessage(sns, message, phoneNumber, smsAttributes);
	}

	public static void sendSMSMessage(AmazonSNS snsClient, String message, 
	    String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
	    PublishResult result = snsClient.publish(new PublishRequest()
	                    .withMessage(message)
	                    .withPhoneNumber(phoneNumber)
	                    .withMessageAttributes(smsAttributes));
	    System.out.println(result); // Prints the message ID.
	}
}
