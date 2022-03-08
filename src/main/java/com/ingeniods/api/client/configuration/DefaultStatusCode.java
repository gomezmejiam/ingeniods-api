package com.ingeniods.api.client.configuration;

import java.util.Optional;

import lombok.Getter;

public enum DefaultStatusCode {
	UNCONTROLED("IA000_ERROR_NO_CONTROLADO", "Se presentó un error no controlado en el sistema"),
	CONFLICT("IA001_PETICION_INCORRECTA", "La petición que está tratando de ejecutar incumple alguna condición"),
	NOT_FOUND("IA002_NO_ENCONTRADA","No se pudo encontrar información asociada para la petición realizada"),
	BAD_REQUEST("IA002_NO_ENCONTRADA","La petición no cumple con el formato establecido");
	
	@Getter
	private String code;
	@Getter
	private String message;
	
	private static final DefaultStatusCode[] VALUES;

	static {
		VALUES = values();
	}
	
	DefaultStatusCode(String codeArg, String messageArg){
		this.code = codeArg;
		this.message = messageArg;
	}
	
	public static Optional<DefaultStatusCode> getByStatusName(String name) {
		for (DefaultStatusCode status : VALUES) {
			if (status.name() == name) {
				return Optional.of(status);
			}
		}
		return Optional.empty();
	}
  
}
