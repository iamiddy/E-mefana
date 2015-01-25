/**
 * 
 */
package com.idrene.emefana.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.idrene.emefana.AbstractIntegrationTest;
import com.idrene.emefana.domain.FileMetadata;
import com.idrene.emefana.domain.FileMetadata.FILETYPE;
import com.idrene.emefana.domain.MetadataFields;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class GridFsServiceTest extends AbstractIntegrationTest{
	
	@Autowired private GridFsService fsService;
	@Autowired private GridFsOperations operations;
	
	@Test
	public void storeFilesTest() throws IOException{
		operations.delete(null);
		Map<String,String> metadataMap= buildMetaDataMapToStore();
		fsService.storeFiles(resource.getInputStream(), metadataMap, (meta) -> new BasicDBObject(meta));
		 // then
		 List<GridFSDBFile> files = operations.find(null);
		 assertTrue(files.size() == 1);
	}
	
	@Test
	public void getThumbnailOrVedeoFileTest(){
		FileMetadata meta = new FileMetadata("iddy", FILETYPE.PHOTO.name(), null);
		GridFSDBFile file = fsService.getThumbnailOrVedeoFile(meta);
		assertNotNull(file);

	}
	
	@Test
	public void getAllProviderPhotosTest(){
		FileMetadata meta = new FileMetadata("iddy", FILETYPE.PHOTO.name(), null);
		List<GridFSDBFile> files = fsService.getAllProviderPhotos(meta);
		assertNotNull(files);
		System.out.println(files.size());
		assertTrue(files.size() > 0);
		assertTrue(files.get(0).getMetaData().get("provider_id").equals(meta.getProviderId()));
	}
	
	private static Map<String,String> buildMetaDataMapToStore(){
		Map<String,String> metadataMap= new HashMap<>();
		metadataMap.put(MetadataFields.CONTENT_TYPE, "jpg");
		metadataMap.put(MetadataFields.FILE_NAME, resource.getFilename());
		metadataMap.put(MetadataFields.TYPE, FILETYPE.PHOTO.name());
		metadataMap.put(MetadataFields.FILE_DESCR, "test file");
		metadataMap.put(MetadataFields.PROVIDER_ID, "iddy");
		metadataMap.put(MetadataFields.THUMBNAIL, "true");
		return metadataMap;
	}

}
