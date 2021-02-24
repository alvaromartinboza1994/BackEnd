'use strict'

var express = require('express');
var FrutaController = require('../controllers/fruta');

var api = express.Router(); //nos permite crear rutas y pasar parametros

api.get('/pruebas', FrutaController.pruebas);

module.exports = api;