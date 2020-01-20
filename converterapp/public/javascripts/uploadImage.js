
const uploadFile = (fileName) => {
    // read content from the file
    var file = document.getElementById('file').files[0];

    const fileContent = fs.readFileSync(fileName);

    console.log(fileContent);
    console.log(file.name)
};