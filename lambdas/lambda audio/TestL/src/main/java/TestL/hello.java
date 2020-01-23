package TestL;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

public class hello implements RequestHandler<RequestClass, ResponseClass> {
	Regions clientRegion = Regions.SA_EAST_1;
	String bucketName = "audiofilesfinal";
	String bucketDestino = "audio-converted";
	
	
	public ResponseClass handleRequest(RequestClass request, Context context) {

		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();
		String log = "hghj";
		
		try {
			S3Object o = s3.getObject(bucketName, request.keyName);
			S3ObjectInputStream s3is = o.getObjectContent();
			File tmp = File.createTempFile("s3test", "");
			Files.copy(s3is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
			File tmp2 = File.createTempFile("s3test2", "");
			Files.copy(s3is, tmp2.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			
			if(request.formato.equalsIgnoreCase("mp3")) {
				// Audio Attributes
				log = "entro a mp3";
				AudioAttributes audio = new AudioAttributes();
				audio.setCodec("libmp3lame");
				audio.setBitRate(request.bitRate);
				audio.setChannels(request.channel);
				audio.setSamplingRate(request.samplingRate);

				// Encoding attributes
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat(request.formato);
				attrs.setAudioAttributes(audio);

				// Encode
				Encoder encoder = new Encoder();
				encoder.encode(new MultimediaObject(tmp), tmp2, attrs);
				PutObjectRequest as = new PutObjectRequest(bucketDestino, request.fileObjKeyName, tmp2);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentDisposition("attachment; filename=" + request.fileObjKeyName);
				as.setMetadata(metadata);
				
				//s3.putObject(bucketDestino, request.fileObjKeyName, tmp2);
				s3.putObject(as);
			}else if(request.formato.equalsIgnoreCase("ac3")) {
				// Audio Attributes
				log = "entro a ac3";
				AudioAttributes audio = new AudioAttributes();
				audio.setCodec("ac3");
				audio.setBitRate(request.bitRate);
				audio.setChannels(request.channel);
				audio.setSamplingRate(request.samplingRate);

				// Encoding attributes
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat(request.formato);
				attrs.setAudioAttributes(audio);

				// Encode
				Encoder encoder = new Encoder();
				encoder.encode(new MultimediaObject(tmp), tmp2, attrs);
				PutObjectRequest as = new PutObjectRequest(bucketDestino, request.fileObjKeyName, tmp2);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentDisposition("attachment; filename=" + request.fileObjKeyName);
				as.setMetadata(metadata);
				
				//s3.putObject(bucketDestino, request.fileObjKeyName, tmp2);
				s3.putObject(as);
			}else if(request.formato.equalsIgnoreCase("ogg")) {
				// Audio Attributes
				log = "entro a ogg";
				AudioAttributes audio = new AudioAttributes();
				audio.setCodec("libvorbis");
				audio.setBitRate(request.bitRate);
				audio.setChannels(request.channel);
				audio.setSamplingRate(request.samplingRate);

				// Encoding attributes
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat(request.formato);
				attrs.setAudioAttributes(audio);

				// Encode
				Encoder encoder = new Encoder();
				encoder.encode(new MultimediaObject(tmp), tmp2, attrs);
				PutObjectRequest as = new PutObjectRequest(bucketDestino, request.fileObjKeyName, tmp2);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentDisposition("attachment; filename=" + request.fileObjKeyName);
				as.setMetadata(metadata);
				
				//s3.putObject(bucketDestino, request.fileObjKeyName, tmp2);
				s3.putObject(as);
			}
			
			
		} catch (AmazonServiceException | IOException | IllegalArgumentException | EncoderException e) {
			System.err.println(e.getLocalizedMessage());
			//System.exit(1);
		}
		String greetingString = String.format("Done %s, a %s.%s", request.keyName, request.formato, log);
		return new ResponseClass(greetingString);
	}
}