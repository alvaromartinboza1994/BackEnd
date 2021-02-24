'use strict'

var express = require('express');
var FrutaController = require('../controllers/fruta');

var api = express.Router(); //nos permite crear rutas y pasar parametros

api.get('/pruebas', FrutaController.pruebas);
api.post('/fruta', FrutaController.saveFruta);
api.get('/frutas', FrutaController.getFrutas);
api.get('/fruta/:id', FrutaController.getFruta);
api.put('/fruta/:id', FrutaController.updateFruta);
api.delete('/fruta/:id', FrutaController.deleteFruta);

module.exports = api;