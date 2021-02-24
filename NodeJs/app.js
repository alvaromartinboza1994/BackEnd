'use strict'

var express = require('express');
var bodyParser = require('body-parser');

var app = express();//cargamos express

//cargar rutas

//body-parser
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

//Configurar CORS

//rutas
app.get('/pruebas-api', (req, res) => {
	res.status(200).send({
		message: 'Esta ruta es de prueba en mi api restful con mongo y node'
	});
})

module.exports = app;//para crear el servidor