
const fs = require('fs');
const AWS = require('aws-sdk');

const uploadFile = () => {
    var file = document.getElementById('file').files[0];

    const fileContent = fs.readFileSync(file);


    const params = {
        Bucket: '',
        Key: file.name, 
        Body: fileContent
    };


    s3.upload(params, function(err, data) {
        if (err) {
            throw err;
        }
        console.log('File uploaded successfully. ${data.Location}');
    });
};