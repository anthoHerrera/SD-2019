var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Converter app' });
});

router.get('/audio', function(req, res, next) {
  res.render('audio', { title: 'Converter Audio' });
});

module.exports = router;
