package com.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.todo.todoapp.CreateTodoDTO;
import com.todo.todoapp.Todo;
import com.todo.todoapp.UpdateTodoDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;


@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter());

        mapper.typeMap(
                CreateTodoDTO.class, 
                Todo.class).addMappings(
                m -> m.using(new LowerCaseConverter()).map(CreateTodoDTO::getCategoryId, Todo::setCategory));

        mapper.typeMap(
                UpdateTodoDTO.class, 
                Todo.class).addMappings(
                m -> m.using(new LowerCaseConverter()).map(UpdateTodoDTO::getCategoryId, Todo::setCategory));
        return mapper;
    }

    private class StringTrimConverter implements Converter<String, String> {

        @Override
        public String convert(MappingContext<String, String> context) {
            if (context.getSource() == null) {
                return null;
            }
            return context.getSource().trim();
        }
    }

     private class LowerCaseConverter implements Converter<String, String> {

        @Override
        public String convert(MappingContext<String, String> context) {
            if (context.getSource() == null) {
                return null;
            }
            return context.getSource().toLowerCase().trim();
        }

    }

}
