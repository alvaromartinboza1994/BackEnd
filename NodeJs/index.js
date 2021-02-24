'use strict'

var mongoose = require('mongoose');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/curse_mongo', {useMongoClient: true})
	.then(() => {
		console.log('La conexión a MongoDB se ha realizado correctamente.');
	})
	.catch(err => console.log(err));