'use strict'

var express = require('express');
var bodyParser = require('body-parser');

var app = express();//cargamos express

//cargar rutas
var fruta_routes = require('./routes/fruta');

//body-parser
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

//Configurar CORS

//rutas
//--> middleware
app.use('api', fruta_routes);


module.exports = app;//para crear el servidor