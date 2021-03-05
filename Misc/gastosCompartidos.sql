CREATE TABLE autentia.persona (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar (20) NOT NULL,
  `apellidos` varchar (20) NOT NULL,
  PRIMARY KEY (`idPersona`)
);

CREATE TABLE autentia.amigo (
  `idAmigo` INT NOT NULL,
  KEY `idAmigo` (`idAmigo`),
  FOREIGN KEY (`idAmigo`) REFERENCES `persona`(`idPersona`)
);

CREATE TABLE autentia.pago (
  `idPago` INT NOT NULL AUTO_INCREMENT,
  `importe` LONG NOT NULL,
  `descripcion` varchar (50) NOT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  `deudores` INT NOT NULL,
  `pagador` INT NOT NULL,
  KEY `idPago` (`idPago`),
  FOREIGN KEY (`deudores`) REFERENCES `amigo`(`idAmigo`),
  FOREIGN KEY (`pagador`) REFERENCES `persona`(`idPersona`)
);

CREATE TABLE autentia.usuario (
  `idUsuario` INT NOT NULL,
  `amigos` INT NOT NULL, 
  `pagosRealizados` INT NOT NULL,
  `deudas` INT NOT NULL,
  KEY `idUsuario` (`idUsuario`),
  FOREIGN KEY (`idUsuario`) REFERENCES `persona`(`idPersona`),
  FOREIGN KEY (`amigos`) REFERENCES `amigo`(`idAmigo`),
  FOREIGN KEY (`pagosRealizados`) REFERENCES `pago`(`idPago`),
  FOREIGN KEY (`deudas`) REFERENCES `pago`(`idPago`)
);

SELECT *
FROM autentia.persona;