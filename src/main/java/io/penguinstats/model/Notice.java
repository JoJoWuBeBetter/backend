package io.penguinstats.model;

import java.io.Serializable;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.penguinstats.enums.Server;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notice")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "The model of a notice.")
public class Notice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	private ObjectId id;
	private Integer severity;
	@JsonProperty("content_i18n")
	private Map<String, String> contentMap;
	private Map<Server, Existence> existence;

	// will be removed
	private ExistConditions conditions;

}
