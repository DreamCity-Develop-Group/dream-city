package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

 public interface FileService {
     Message deleteFileById(Message msg)throws BusinessException;

     Message insertFile(Message msg)throws BusinessException;

     Message getFileById(Message msg)throws BusinessException;

     Message getFileList(Message msg)throws BusinessException;

     Message updateFileById(Message msg)throws BusinessException;
}
