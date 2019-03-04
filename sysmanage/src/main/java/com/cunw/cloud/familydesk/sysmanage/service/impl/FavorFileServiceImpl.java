package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.sysmanage.mapper.FavorFileMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IFavorFileService;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.model.CloudFile;
import com.cunw.cloud.school.common.model.FavorFile;
import com.cunw.cloud.school.common.model.FavorResFile;
import com.cunw.cloud.school.common.model.FileSrcEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FavorFile服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:22
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：FavorFile服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "favorFileService")
public class FavorFileServiceImpl extends BaseServiceImpl<FavorFile, String> implements IFavorFileService {

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Autowired
    private FavorFileMapper favorFileMapper;

    @Override
    protected IBaseMapper<FavorFile, String> getMapper() {
        return favorFileMapper;
    }

    @Override
    public FavorFile addFromCloudFile(FavorFile favorFile) {
        CloudFile cloudFile = ServerRestTemplate.get(resourceServerUrl + "/resource/clouditems/" + favorFile.getFileId(), CloudFile.class);
        log.info("cloudFile list >>", JsonMapper.nonEmptyMapper().toJson(cloudFile));
        if (cloudFile == null)
            return null;
        favorFile.setFileName(cloudFile.getTitle());
        favorFile.setFileUrl(cloudFile.getDownloadUrl());
        return add(favorFile);
    }

    @Override
    public List<FavorResFile> queryByFavorite(String favorId, String titleName) {
        List<FavorFile> favs = queryByProperty("favorId", favorId);
        if (CollectionUtils.isEmpty(favs))
            return null;
        String ids = favs.stream().map(file -> file.getFileId()).collect(Collectors.joining(",")).toString();
        List<FavorResFile> list = ServerRestTemplate.getList(resourceServerUrl + "/resource/clouditems/ids?ids=" + ids +"&titleName="+titleName, FavorResFile.class);
        if (CollectionUtils.isEmpty(list))
            return list;
        log.info("favs list >>", JsonMapper.nonEmptyMapper().toJson(favs));
        Map<String, String> favMap = favs.stream().collect(Collectors.toMap(FavorFile::getFileId, FavorFile::getFavorFileId));
        list.stream().forEach(file ->
            {
                file.setFavorFileId(favMap.get(file.getResId()));
                favs.forEach(fa -> {
                    if(file.getResId().equals(fa.getFileId())){
                        if(fa.getFileSrc().equals(FileSrcEnum.DISK.getCode())){
                            file.setFileSrc(FileSrcEnum.DISK.getName());
                        }else if(fa.getFileSrc().equals(FileSrcEnum.COURSE.getCode())){
                            file.setFileSrc(FileSrcEnum.COURSE.getName());
                        }else if(fa.getFileSrc().equals(FileSrcEnum.CLOUD.getCode())){
                            file.setFileSrc(FileSrcEnum.CLOUD.getName());
                        }else{
                            file.setFileSrc(FileSrcEnum.OTHER.getName());
                        }
                        file.setFilecrtDate(fa.getCrtDate());
                        return;
                    }

                });

            }
        );
        return list;
    }
}
