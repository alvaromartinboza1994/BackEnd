'use strict'

function pruebas (req, res) {
	res.status(200).send({
		message: 'Esta ruta es de prueba en mi api restful con mongo y node'
	});
}

module.exports = {
	pruebas
};