--Roles
INSERT INTO `rol` (`id_rol`, `descripcion`, `nombre`) VALUES ('1', 'Todas las funciones del Sistema', 'Administrador');
INSERT INTO `rol` (`id_rol`, `descripcion`, `nombre`) VALUES ('2', 'Publicaciones', 'Usuario');

--Personas

INSERT INTO `persona` (`id_persona`, `apellido`, `dni_pasaporte`, `email`, `fecha_nac`, `nombre`, `telefono`, `celular`) VALUES ('1', 'Crespo', '0150977676', 'christiancrespoort@gmail.com', '2001-12-08', 'Christian', '2807127', '0962670797');
INSERT INTO `persona` (`id_persona`, `apellido`, `dni_pasaporte`, `email`, `fecha_nac`, `nombre`, `telefono`, `celular`) VALUES ('2', 'Gaona', '1105694721', 'jhudytg@gmail.com', '2003-02-01', 'Jhudyt', '2807127', '0962670797');

--Usuarios

--Usuarios

INSERT INTO usuario (id_usuario, contrasenia, estado, nombre_usuario, id_persona, id_rol) VALUES ('1', 'Lolito@12', 'Activo', 'christianC81', '1', '1');

INSERT INTO usuario (id_usuario, contrasenia, estado, nombre_usuario, id_persona, id_rol) VALUES ('2', 'Lolito@12', 'Inactivo', 'jhudytg', '2', '1');

--Salones

--INSERT INTO `salon` (`capacidad`, `disponibilidad`, `id_salon`, `id_usuariopublicador`, `latitud`, `longitud`, `precio_salon`, `nombre`, `categoria`, `descripcion`, `direccion`, `estado`, `foto`) VALUES ('100',1, '1', '1', '10.2', '123.2', '100', 'SalonGeltr', 'Cumpleaños', 'Grande', 'Av. pumapungo', 'Activo', 'assdsad');