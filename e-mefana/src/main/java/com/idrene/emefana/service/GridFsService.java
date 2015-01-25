/**
 * 
 */
package com.idrene.emefana.service;

import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.idrene.emefana.domain.FileMetadata;
import com.idrene.emefana.domain.FileMetadata.FILETYPE;
import com.idrene.emefana.domain.MetadataFields;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * @author iddymagohe
 * @since 1.0
 */
public interface GridFsService {
	
	/**
	 * @param content
	 * @param metadata
	 * @param dbObject
	 */
	public void storeFiles(InputStream content, Map<String,String> metadata, Function<Map<String,String>, DBObject> dbObject);
	public GridFSDBFile getThumbnailOrVedeoFile(FileMetadata searchCriteria);
	public List<GridFSDBFile> getAllProviderPhotos(FileMetadata searchCriteria);

}

@Service
class EmefanaGridFsServiceImpl implements GridFsService{
    
	//TODO pass filename to search queries for improved performance
	
	  @Autowired
	  private GridFsOperations operations;
	  private static final String  METADATAFIELD="metadata.";


	@Override
	public void storeFiles(final InputStream content,final Map<String,String> metadata, Function<Map<String,String>, DBObject> dbObject) {
		Assert.notNull(content, "file content can not be null");
		Assert.notNull(metadata, " Meta data can not be null");
		operations.store(content, metadata.get(MetadataFields.FILE_NAME), metadata.get(MetadataFields.CONTENT_TYPE),dbObject.apply(metadata));
	}


	@Override
	public GridFSDBFile getThumbnailOrVedeoFile(final FileMetadata searchCriteria) {
		Assert.notNull(searchCriteria,"search criteria for thumbnail can not be null");
		Criteria criteria = new Criteria(getMetaField(MetadataFields.TYPE)).is(searchCriteria.oType().orElse(FILETYPE.PHOTO.name()));
		searchCriteria.oProviderId().ifPresent(provider -> {
			criteria.and(getMetaField(MetadataFields.PROVIDER_ID)).is(provider);
			searchCriteria.oType().ifPresent(type -> {
				if (type.equalsIgnoreCase(FILETYPE.PHOTO.name())) {
					criteria.and(getMetaField(MetadataFields.THUMBNAIL)).is("true");
				}
			});
		});
		
		searchCriteria.oUserId().ifPresent(user -> criteria.and(getMetaField(MetadataFields.USER_ID)).is(user));
		return operations.findOne(query(criteria));
	}


	@Override
	public List<GridFSDBFile> getAllProviderPhotos(FileMetadata searchCriteria) {
		Assert.notNull(searchCriteria,"Search criteria for thumbnail can not be null");
		Criteria criteria = new Criteria(getMetaField(MetadataFields.TYPE)).is(searchCriteria.oType().orElse(FILETYPE.PHOTO.name()));
		searchCriteria.oProviderId().ifPresent(provider -> criteria.and(getMetaField(MetadataFields.PROVIDER_ID)).is(provider));
		return operations.find(query(criteria));
	}
	
	static String getMetaField(String field) {
		return field.contains(METADATAFIELD ) ? field : METADATAFIELD + field;
	}
	
	
	
	}
