POLICY_JSON = {
    "expiration": "2020-12-01T12:00:00.000Z",
    "conditions": [
        { "bucket": this.get('bucket') },
        ["starts-with", "$key", ""],
        { "acl": this.get('acl') },
        ["starts-with", "$Content-Type", ""],
        ["content-length-range", 0, 524288000]
    ]
};

var secret = this.get('AWSSecretKeyId');
var policyBase64 = Base64.encode(JSON.stringify(POLICY_JSON));
console.log(policyBase64)

var signature = b64_hmac_sha1(secret, policyBase64);
b64_hmac_sha1(secret, policyBase64);
console.log(signature);

function uploadFile() {

    var file = document.getElementById('file').files[0];
    var fd = new FormData();

    var key = "events/" + (new Date).getTime() + '-' + file.name;

    fd.append('key', key);
    fd.append('acl', 'public-read');
    fd.append('Content-Type', file.type);
    fd.append('AWSAccessKeyId', 'YOUR ACCESS KEY');
    fd.append('policy', 'YOUR POLICY')
    fd.append('signature', 'YOUR SIGNATURE');

    fd.append("file", file);

    var xhr = getXMLHTTPObject();

    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);

    xhr.open('POST', 'https://<yourbucket>.s3.amazonaws.com/', true); //MUST BE LAST LINE BEFORE YOU SEND 

    xhr.send(fd);
}