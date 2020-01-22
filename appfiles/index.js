const express = require('express');
const morgan = require('morgan')
const app = express();

//settings
app.set('port', 3000);
app.set('view engine', 'ejs');
//Middlewares
app.use(express.json());
app.use(morgan('dev'));

//routes
app.get('/', (req, res) => {
    res.render('index.ejs');
});

app.get('/audio', (req, res) => {
    res.render('audio.ejs');
});

app.get('/image', (req, res) => {
    res.render('image.ejs');
});

app.get('/docs',(req,res)=>{
	res.render('document.ejs')
});

app.use(express.static('public'))

app.listen(app.get('port'), () => {
    console.log('Serve on port', app.get('port'));
});
