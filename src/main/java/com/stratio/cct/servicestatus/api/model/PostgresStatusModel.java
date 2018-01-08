package com.stratio.cct.servicestatus.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostgresStatusModel {

	List<PostgresNodeStatusModel> status;
}
