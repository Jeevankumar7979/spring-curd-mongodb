package com.jeevankumar.spring_crud_mongodb.config;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration
public class GraphqlConfig {

    @Bean
    public GraphQLScalarType longScalar() {
        return ExtendedScalars.GraphQLLong;
    }

    @Bean
    public GraphQLScalarType localDateScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("LocalDate as scalar.")
                .coercing(new Coercing<LocalDate, String>() {

                    @Override
                    public @NotNull String serialize(@NotNull Object dataFetcherResult,
                                                     @NotNull GraphQLContext graphQLContext, @NotNull Locale locale)
                            throws CoercingSerializeException {
                        if (dataFetcherResult instanceof LocalDate) {
                            return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                        throw new CoercingSerializeException(
                                "Expected a LocalDate object but was: " + dataFetcherResult.getClass());
                    }

                    @Override
                    public @NotNull LocalDate parseValue(@NotNull Object input,
                                                         @NotNull GraphQLContext graphQLContext, @NotNull Locale locale)
                            throws CoercingParseValueException {
                        try {
                            if (input instanceof String) {
                                return LocalDate.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE);
                            }
                            throw new CoercingParseValueException(
                                    "Expected a String but was: " + input.getClass());
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format(
                                    "Not a valid LocalDate: '%s'. Expected format: 'yyyy-MM-dd'", input));
                        }
                    }

                    @Override
                    public @NotNull LocalDate parseLiteral(@NotNull Value<?> input,
                                                           @NotNull CoercedVariables variables, @NotNull GraphQLContext graphQLContext,
                                                           @NotNull Locale locale) throws CoercingParseLiteralException {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(
                                        "Failed to parse LocalDate literal: " + input, e);
                            }
                        }
                        throw new CoercingParseLiteralException(
                                "Expected StringValue but was: " + input.getClass());
                    }
                })
                .build();
    }


   @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return wiringBuilder -> wiringBuilder
                .scalar(localDateScalar())
                .scalar(longScalar());

    }

}
