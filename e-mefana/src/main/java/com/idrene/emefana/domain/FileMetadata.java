/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.Optional;

import lombok.Getter;

import com.idrene.emefana.util.UtilityBean;
import com.mongodb.gridfs.GridFSDBFile;


/**
 * @author iddymagohe
 * @since 1.0
 *
 */

public class FileMetadata {

	@Getter
	private String providerId;
	@Getter
	private String type;
	@Getter
	private boolean thumbnail;
	@Getter
	private String userId;
	@Getter
	private String fileName;
	@Getter
	private String contentType;
	@Getter 
	private String image;

	public FileMetadata(Optional<GridFSDBFile> fileData) {
		fileData.ifPresent(file -> {
			contentType = file.getContentType();
			fileName = file.getFilename();
			FileMetadata.fieldAsString(file, MetadataFields.TYPE).ifPresent(value -> type = value);
			FileMetadata.fieldAsString(file, MetadataFields.THUMBNAIL).ifPresent(value -> thumbnail = Boolean.valueOf(value));
			FileMetadata.fieldAsString(file, MetadataFields.PROVIDER_ID).ifPresent(value -> providerId = value);
			FileMetadata.fieldAsString(file, MetadataFields.USER_ID).ifPresent(value -> userId = value);
			try {
				UtilityBean.InputStreamToBase64(Optional.ofNullable(file.getInputStream())).ifPresent(str -> image=str);
			} catch (Exception e) {
				//TODO deal with this exception here, log it and move on 
			}
		});
	}
	
	public static Optional<String> fieldAsString(final GridFSDBFile file,final String field) {
		return Optional.ofNullable(String.valueOf(file.get(field)));
	}
	
	

}
