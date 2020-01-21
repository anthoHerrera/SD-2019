
const uploadFile = (fileName) => {
    // read content from the file
    var file = document.getElementById('file-input').files[0];
    console.log(file.name)
};

$('#button').on('click', function() {
    $('#file-input').trigger('click');
});

 $( "#file-input" ).change(function() {
    var file = document.getElementById('file-input').files[0];
    $("#fileName").html(file.name); 
  });