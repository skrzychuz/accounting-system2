package pl.coderstrust.database.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class MapperConfig {
    private ObjectMapper mapper;

  public MapperConfig() {
    this.mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
  }

    public ObjectMapper getMapper() {
        return mapper;
    }
}