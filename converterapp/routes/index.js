var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Converter app' });
});

router.get('/audio', function(req, res, next) {
  res.render('audio', { title: 'Converter Audio' });
});

router.get('/image', function(req, res, next) {
  res.render('image', { title: 'Image Converter' });
});

module.exports = router;
