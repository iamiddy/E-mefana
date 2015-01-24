/**
 * 
 */
package com.idrene.emefana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.*;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.idrene.emefana.domain.FileMetadata;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * @author iddymagohe
 * @since 1.0
 */
public interface GridFSService {

}

class GridFsClient implements GridFSService{

	  @Autowired
	  GridFsOperations operations;

	 
//	  public void storeFileToGridFs {
//
    //FileMetadata metadata = new FileMetadata();
//	    // populate metadata
//	   // Resource file = … // lookup File or Resource
//
//	    operations.store(file.getInputStream(), "filename.txt", metadata);
//	  }
    
    public void findFilesInGridFs() {
        List<GridFSDBFile> result = operations.find(query(whereFilename().is("filename.txt")));
      }
	}
