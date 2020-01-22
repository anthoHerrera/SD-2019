
/*
const uploadFile = (fileName) => {
    // read content from the file
    var file = document.getElementById('file-input').files[0];
    console.log(file.name);

    const params = {
        Bucket: BUCKET_NAME,
        Key: 'cat.jpg', // File name you want to save as in S3
        Body: file
    };

    console.log(params);
};*/

$('#button').on('click', function () {
    $('#file-input').trigger('click');
});

$("#file-input").change(function () {
    var file = document.getElementById('file-input').files[0];
    $("#fileName").html(file.name);
});