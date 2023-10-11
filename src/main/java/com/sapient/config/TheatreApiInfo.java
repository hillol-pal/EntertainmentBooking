package com.sapient.config;


import com.sapient.model.Theatre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/v1/defineShows",
                operation =
                @Operation(
                        description = "Define Shows for a Theatre",
                        operationId = "defineShows",
                        tags = "Post Shows By Date For A Theatre",

                        requestBody =
                        @RequestBody(
                                description = "Add Shows By Date With Show Schedule",
                                required = true,
                                content = @Content(schema = @Schema(implementation = Theatre.class))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Added Theatre data returned back",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Theatre.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/v1/findById/{doc_id}",

                operation =
                @Operation(
                        description = "Get Theatre Shows By date For Theatre By Identifier",
                        operationId = "findById",
                        parameters = { @Parameter(in = ParameterIn.PATH, name = "doc_id", description = "Document Object Id") },
                        tags = "Find By ID",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Find By Document Id",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Theatre.class))
                                        }),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "Invalid Document Id"
                                ),
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/v1/findByDateAndTheater",
                operation =
                @Operation(
                        description = "Get Theater Show details by Date and Theater",
                        operationId = "getTheaterShowsByDateAndTheater",
                        tags = "Find By Date And Theater",
                        parameters = {
                                @Parameter(in = ParameterIn.QUERY, name = "date", description = "Date For Shows"),
                                @Parameter(in = ParameterIn.QUERY, name = "theater_name", description = "Theater Name")
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Find By Date And Theater",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Theatre.class))
                                        }),
                        })),
        @RouterOperation(
                method = RequestMethod.DELETE,
                path = "v1/deleteById/{doc_id}",
                operation =
                @Operation(
                        description = "Delete Theatre Shows  By Identifier",
                        operationId = "DeleteShowsByTheaterThroughIdentifier",
                        tags = "Delete Theatre Shows By Identifier",
                        parameters = {
                                @Parameter(in = ParameterIn.PATH, name = "doc_id", description = "Document ID"),
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "204",
                                        description = "Delete By Document Id"
                                ),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "Invalid Document Id"
                                ),

                        })),
        @RouterOperation(
                method = RequestMethod.DELETE,
                path = "/v1/deleteByDateAndTheater",
                operation =
                @Operation(
                        description = "Delete by Date and Theater",
                        operationId = "deleteTheaterShowsByDateAndTheater",
                        tags = "Delete By Date And Theater",
                        parameters = {
                                @Parameter(in = ParameterIn.QUERY, name = "date", description = "Date For Shows"),
                                @Parameter(in = ParameterIn.QUERY, name = "theater_name", description = "Theater Name")
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "204",
                                        description = "Find By Date And Theater"),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "No Document Found By the Id and Theater Name"
                                ),
                        })),
        @RouterOperation(
                method = RequestMethod.PUT,
                path = "/v1/replaceById/{doc_id}",
                operation =
                @Operation(
                        description = "Replace Document By ID",
                        operationId = "ReplaceTheaterShowsForDateByIdentifier",
                        tags = "Replace Theater Shows For A Date By Identifier",
                        parameters = {
                                @Parameter(in = ParameterIn.PATH, name = "doc_id", description = "Document Id"),

                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Replaced Theatre data",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Theatre.class))
                                        })
                        }))

})
public @interface TheatreApiInfo {}
