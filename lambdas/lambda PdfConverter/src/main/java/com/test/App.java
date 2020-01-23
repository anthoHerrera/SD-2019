package com.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.List;



import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.spire.pdf.*;


/**
 * AWS Lambda function with S3 trigger.
 * 
 */
public class App implements RequestHandler<S3EventNotification, String> {
	
	Regions clientRegion = Regions.SA_EAST_1;
	String bucketName = "bucket-docfiles";
    String key = null;


	@Override
	public String handleRequest(S3EventNotification s3EventNotification, Context context) {
		
		final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .build();
		
		List<S3EventNotification.S3EventNotificationRecord> records = s3EventNotification.getRecords();
		this.bucketName = records.get(0).getS3().getBucket().getName();
		this.key = records.get(0).getS3().getObject().getKey();
		S3Object document = s3Client.getObject(new GetObjectRequest(this.bucketName, this.key));
		
		String[] array = key.split("\\.");
	
		String prefix = array[0];
		PdfDocument doc = new PdfDocument();
		String nameNewDoc = prefix+".doc";
		S3ObjectInputStream s3is = null;
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
		    
		    s3is = document.getObjectContent();
		    
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
		System.out.println("Cargando archivo a pdf");
		doc.loadFromStream(s3is);
	    doc.saveToStream(outStream, FileFormat.DOC);
	    System.out.println("Saved in Stream");
	    try {
			s3is.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ByteArrayInputStream inStream = new ByteArrayInputStream( outStream.toByteArray() );
		 ObjectMetadata metadata = new ObjectMetadata();
         metadata.setContentType("plain/text");
         metadata.addUserMetadata("x-amz-meta-title", "someTitle");
         s3Client.putObject("bucket-docfiles-converted", nameNewDoc, inStream, metadata);
         try {
			inStream.close();
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}