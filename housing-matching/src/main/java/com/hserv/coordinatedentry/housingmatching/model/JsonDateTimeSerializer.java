package com.hserv.coordinatedentry.housingmatching.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
	@Override
	public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider)
	throws IOException, JsonProcessingException {
	String formattedDate = dateFormat.format(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
	gen.writeString(formattedDate);
	}

}
