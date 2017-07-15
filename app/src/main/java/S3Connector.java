import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class S3Connector {
    private AmazonS3 s3Client;


    public S3Connector(String accessKey, String secretKey, Regions region) {
        BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds))
                .withRegion(region)
                .build();
    }

    public S3Connector(String credentialsFilePath, Regions region) {
        ProfileCredentialsProvider credProvider = new ProfileCredentialsProvider(credentialsFilePath, "default");
        credProvider.getCredentials();
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(region)
                .build();
    }

    // @bucket: bucket on S3, should be constant
    // @key : S3 file path
    // @filePath : local path
    public void createImage(String bucket, String key, String filePath) {

        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(filePath);
            s3Client.putObject(new PutObjectRequest(bucket, key, file));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public String readImage(String bucket, String key, String imageType) {
        String filepathOfImage = parseImageInputStream(getInputStream(bucket, key), imageType);
        return filepathOfImage;
    }

    public void deleteImage(String bucket, String key) {
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucket, key));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }


    public InputStream getInputStream(String bucket, String key) {
        InputStream objectData = null;

        try {
            S3Object object = s3Client.getObject(new GetObjectRequest(bucket, key));
            objectData = object.getObjectContent();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" +
                " means your request made it " +
                "to Amazon S3, but was rejected with an error response" +
                " for some reason.");
            System.out.println("Error Message:      " + ase.getMessage());
            System.out.println("HTTP Status CodeL   " + ase.getStatusCode());
            System.out.println("AWS Error Code:     " + ase.getErrorCode());
            System.out.println("Error Type:         " + ase.getErrorType());
            System.out.println("Request ID:         " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means" +
                " the client encountered " +
                " an internal error while trying to " +
                "communicate with S3, " +
                "such as not being able to access the network.");
            System.out.println("Error Message:      " + ace.getMessage());
        }

        return objectData;
    }

    //DEBUG PRINT FUNCTION
    public static void displayTextInputStream(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Failed to read from InputStream");
            }

            if (line == null) {
                break;
            }

            System.out.println(line);
        }
        System.out.println();
    }

    public static String parseImageInputStream(InputStream input, String imageType) {
        BufferedImage bufferedImage = null;

        //TODO: dont printStackTrace as exception handling
        try {
            bufferedImage = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String targetPath = "src/main/resources/images/test." + imageType;

        File outputfile = new File(targetPath);
        try {
            ImageIO.write(bufferedImage, imageType, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return targetPath;
    }

    public static void main(String[] args) {
        S3Connector test = new S3Connector("src/main/resources/credentials", Regions.US_WEST_1);

        /*
        InputStream s3DataStream = test.getInputStream("neilauctionphotos", "test/test.jpg");
        parseImageInputStream(s3DataStream, "jpg");
        */

        //String filePath = test.getImage("neilauctionphotos", "test/test.jpg", "jpg");

        test.createImage("neilauctionphotos", "test/test.jpg", "src/main/resources/images/test.jpg");


    }

}
