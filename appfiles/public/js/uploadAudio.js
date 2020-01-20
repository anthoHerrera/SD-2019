const AWS = require('aws-sdk');
const fs = require('fs');

// Enter copied or downloaded access id and secret here
const ID = '';
const SECRET = '';

// Enter the name of the bucket that you have created here
const BUCKET_NAME = 'test-bucket-1242tsr';;


// Initializing S3 Interface
const s3 = new AWS.S3({
    accessKeyId: ID,
    secretAccessKey: SECRET
});

const uploadFile = (fileName) => {
    // read content from the file
    var file = document.getElementById('file').files[0];

    const fileContent = fs.readFileSync(fileName);

    // setting up s3 upload parameters
    const params = {
        Bucket: BUCKET_NAME,
        Key: 'cat.jpg', // file name you want to save as
        Body: fileContent
    };

    // Uploading files to the bucket
    s3.upload(params, function(err, data) {
        if (err) {
            throw err
        }
        console.log(`File uploaded successfully. ${data.Location}`)
    });
};
