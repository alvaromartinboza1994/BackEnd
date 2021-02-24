'use strict'

var Fruta = require('../models/fruta');

function pruebas (req, res) {
	res.status(200).send({
		message: 'Esta ruta es de prueba en mi api restful con mongo y node'
	});
}

function saveFruta(req, res) {
	var fruta = new Fruta();

	var params = req.body;

	if(params.nombre) {
		fruta.nombre = params.nombre;
		fruta.color = params.color;
		fruta.temporada = params.temporada;

		fruta.save((err, frutaStored) => {
			if(err) {
				res.status(500).send({
					message: 'Error en el servidor'
				});
			} else {
				if(frutaStored){
					res.status(200).send({
						fruta: frutaStored
					}):
				} else {
					res.status(200).send({
						message: 'No se ha guardado la fruta'
					});
				}
			}
		});
	} else {
			res.status(200).send({
					message: 'El nombre de la fruat es obligatorio'
				});
		}
}

module.exports = {
	pruebas,
	saveFruta
};